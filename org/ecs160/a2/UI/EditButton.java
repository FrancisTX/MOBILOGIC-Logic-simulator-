package org.ecs160.a2.UI;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.RoundRectBorder;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.ViewWorkspace;

public class EditButton extends Container {
    public Button button;
    public EditButton(){

        button = new Button("Edit");
        button.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        button.getAllStyles().setBorder(RoundRectBorder.create());
    }


    public void showEditPopup(ViewWorkspace workspace){
        Dialog numInputsEditBox = createDialogBox(workspace);
        numInputsEditBox.showPopupDialog(button);
    }


    public Dialog createDialogBox(ViewWorkspace workspace){
        Dialog numInputsEditBox = new Dialog("Change Number of Inputs");
        Label numInputsEditPrompt = new Label("Choose a value between 2 and 5");
        TextField numInputsEntryField  = new TextField("","",2,TextArea.NUMERIC);
        Button acceptEditButton = new Button("Accept Input");
        acceptEditButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        acceptEditButton.getAllStyles().setBorder(RoundRectBorder.create());
        acceptEditButton.addActionListener((e) -> {  //ABSTRACT THIS BY PASSING INT FROM GETTEXT.NUMINPUTSENTRYFIELD AND RETURN NEW FULLY FUNCTIONAL BUTTON
            try{
                int newInputVal = Integer.parseInt(numInputsEntryField.getText());
                callEditInput(newInputVal,numInputsEditBox,workspace);
            }
            catch(NumberFormatException error){
                numInputsEditBox.dispose();
                editInputError(numInputsEditBox);
            }

        });
        numInputsEditBox.add(numInputsEditPrompt);
        numInputsEditBox.add(numInputsEntryField);
        numInputsEditBox.add(acceptEditButton);
        return numInputsEditBox;
    }


    public void callEditInput (int newInputVal, Dialog numInputsEditBox, ViewWorkspace workspace){

        if ((newInputVal <= 5) && (newInputVal >=2) ) {
            editNumOfInputs(newInputVal);
            numInputsEditBox.dispose();
            workspace.repaint();
        }
        else {
            numInputsEditBox.dispose();
            editInputError(numInputsEditBox);
        }

    }

    public void editInputError(Dialog numInputsEditBox){
        TextArea errorMsg = new TextArea("Invalid value entered",1,11);
        errorMsg.getAllStyles().setFgColor(0xff0000);
        numInputsEditBox.add(errorMsg);
        numInputsEditBox.showPopupDialog(button);
    }

    public void editNumOfInputs(int newNumofGates){
        Widget targetItem = WorkspaceUtil.getInstance().getHighlightedWidget();
        if (targetItem instanceof LogicGate) {
            ((LogicGate) targetItem).changeInputSize(newNumofGates);
        }
        targetItem.setCoordinates(targetItem.getX()+1, targetItem.getY());
    }
}
