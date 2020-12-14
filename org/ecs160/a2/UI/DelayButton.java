package org.ecs160.a2.UI;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.RoundRectBorder;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.ViewWorkspace;

public class DelayButton extends Container{
    public Button button;

    public DelayButton(){
        button = new Button("Delay");
        button.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        button.getAllStyles().setBorder(RoundRectBorder.create());
    }

    public void showDelayPopup(ViewWorkspace workspace){
        Dialog numInputsEditBox = createDialogBox(workspace);
        numInputsEditBox.showPopupDialog(button);
    }

    public Dialog createDialogBox(ViewWorkspace workspace){
        Dialog setThePropagationDelay = new Dialog("Set propagation delay");
        TextField setDelayTime  = new TextField("","",2,TextArea.NUMERIC);
        Button acceptDelayButton = new Button("Set");
        acceptDelayButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        acceptDelayButton.getAllStyles().setBorder(RoundRectBorder.create());
        acceptDelayButton.addActionListener((e) -> {
            try {
                int delayTime = Integer.parseInt(setDelayTime.getText());
                Log.p(String.valueOf(delayTime)+"Set");
                callDelayInput(delayTime, setThePropagationDelay, workspace);
            }
            catch(NumberFormatException error){
                setThePropagationDelay.dispose();
                editInputError(setThePropagationDelay);
            }
        });
        setThePropagationDelay.add(setDelayTime);
        setThePropagationDelay.add(acceptDelayButton);
        return setThePropagationDelay;
    }

    public void callDelayInput (int delayTime, Dialog setThePropagationDelay, ViewWorkspace workspace){

        if ((delayTime < 1000) && (delayTime > 0)) {
            setDelayTime(delayTime);
            setThePropagationDelay.dispose();
            workspace.repaint();
        }
        else {
            setThePropagationDelay.dispose();
            editInputError(setThePropagationDelay);
        }

    }
    public void editInputError(Dialog setThePropagationDelay){
        TextArea errorMsg = new TextArea("Invalid value entered",1,11);
        errorMsg.getAllStyles().setFgColor(0xff0000);
        setThePropagationDelay.add(errorMsg);
        setThePropagationDelay.showPopupDialog(button);
    }

    public void setDelayTime(int delayTime){
        Widget targetItem = WorkspaceUtil.getInstance().getHighlightedWidget();
        if (targetItem instanceof LogicGate) {
            ((LogicGate) targetItem).changeDelayTime(delayTime);
        }
        targetItem.setCoordinates(targetItem.getX()+1, targetItem.getY());
    }
}
