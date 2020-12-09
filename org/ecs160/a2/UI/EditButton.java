package org.ecs160.a2.UI;

import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.ViewWorkspace;

import java.awt.*;
import java.io.IOException;

public class EditButton extends Container {
    private Button EditButton;
    public EditButton(ViewWorkspace workspace){

        EditButton = new Button("Edit");
        this.add(EditButton);
        EditButton.addActionListener(evt -> {
            if (WorkspaceUtil.getInstance().getHighlightedWidget() == null){
                return;
            }
            this.showEditPopup(workspace);
            //workspace.repaint();
        });

    }


    public void showEditPopup(ViewWorkspace workspace){
        Dialog numInputsEditBox = createDialogBox(workspace);
        //Label test = new Label("test");
        numInputsEditBox.showPopupDialog(this);
    }


    public Dialog createDialogBox(ViewWorkspace workspace){
        Dialog numInputsEditBox = new Dialog("Change Number of Inputs");
        Label numInputsEditPrompt = new Label("Choose a value between 2 and 5");
        TextField numInputsEntryField  = new TextField("","",2,TextArea.NUMERIC);
        Button acceptEditButton = new Button("Accept Input");
        acceptEditButton.addActionListener((e) -> {  //ABSTRACT THIS BY PASSING INT FROM GETTEXT.NUMINPUTSENTRYFIELD AND RETURN NEW FULLY FUNCTIONAL BUTTON
            try{
                int newInputVal = Integer.parseInt(numInputsEntryField.getText());
                callEditInput(newInputVal,numInputsEditBox,workspace);
                //numInputsEditBox.dispose();

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
            //System.out.println(newInputVal);
            editNumOfInputs(newInputVal);  //CHANGE TO CALL EDIT HIGHLIGHTED FUNCTION, MIGHT NEED TO PASS WORKSPACE AS PARAMETER
            numInputsEditBox.dispose();
            workspace.repaint();
        }
        else {
            numInputsEditBox.dispose();
            editInputError(numInputsEditBox);
        }

    }

    public void editInputError(Dialog numInputsEditBox){
        TextArea errorMsg = new TextArea("Invalid value entered",2,12);
        errorMsg.getAllStyles().setFgColor(0xff0000);
        numInputsEditBox.add(errorMsg);
        numInputsEditBox.showModeless();
    }

    public void editNumOfInputs(int newNumofGates){
        Widget targetItem = WorkspaceUtil.getInstance().getHighlightedWidget();
        if (targetItem instanceof LogicGate) {
            ((LogicGate) targetItem).changeInputSize(newNumofGates);
        }
        targetItem.setCoordinates(targetItem.getX()+1, targetItem.getY());
    }
}
