package org.ecs160.a2;
import com.codename1.components.MultiButton;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import org.ecs160.a2.Objects.Gate.GateAND;
import com.codename1.ui.Command;
import org.ecs160.a2.UI.EditButton;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Utilities.WidgetFactory;
import org.ecs160.a2.Utilities.WorkspaceUtil;

import java.io.IOException;

public class ViewMenu extends Container {
    private ViewWorkspace workspace;

    public ViewMenu(ViewWorkspace workspace) {
        super();
        this.workspace = workspace;
        this.setLayout(new GridLayout(1, 4));
        this.getAllStyles().setBgColor(0xffffff);
        //this.getAllStyles().setBgTransparency(255);

        Button SaveButton = new Button("Save");
        SaveButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        SaveButton.getAllStyles().setBorder(RoundRectBorder.create());
        SaveButton.addActionListener((evt) -> {
            TextField circuitName = new TextField("", "Circuit Name", 20, TextArea.ANY);
            Command enter = new Command("Ok");
            if(Dialog.show("Save the Circuit", circuitName, enter, new Command("Cancel")) == enter){
                if(!circuitName.getText().isEmpty()) {
                    workspace.mainCircuit.save(circuitName.getText());
                }
            }
        });
        this.add(SaveButton);

        Button LoadButton = new Button("Load");
        LoadButton.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        LoadButton.getAllStyles().setBorder(RoundRectBorder.create());
        LoadButton.addActionListener((evt)->{
            String[] files = Storage.getInstance().listEntries();
            Dialog circuitSelect = new Dialog("Load the Circuit", BoxLayout.y());
            Container fileSelectorContainer = new Container(BoxLayout.y());
            fileSelectorContainer.setScrollableY(true);
            Button cancel = new Button("Cancel");
            cancel.getAllStyles().setBgColor(Config.getInstance().cancelButtonColor);
            cancel.getAllStyles().setBorder(RoundRectBorder.create());
            cancel.addActionListener(evt2->{
                circuitSelect.dispose();
            });
            for(int iter = 0; iter < files.length; iter++){
                MultiButton fileSelector = new MultiButton(files[iter]);
                fileSelector.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
                fileSelector.getAllStyles().setBorder(RoundRectBorder.create());
                String circuitName = files[iter];
                fileSelector.addActionListener((evt1)->{
                    workspace.loadMain(circuitName);
                    circuitSelect.dispose();
                });
                fileSelectorContainer.add(fileSelector);
            }
            circuitSelect.add(fileSelectorContainer);
            circuitSelect.add(cancel);
            circuitSelect.show();
        });
        this.add(LoadButton);

        Button Trash = new Button("Remove");
        Trash.getAllStyles().setBgColor(Config.getInstance().taskButtonColor);
        Trash.getAllStyles().setBorder(RoundRectBorder.create());
        Trash.addActionListener(evt -> {
            workspace.removeHighlighted();
            workspace.repaint();
        });
        this.add(Trash);

        EditButton edit = new EditButton(workspace);
        edit.button.addActionListener(evt -> {
            if (WorkspaceUtil.getInstance().getHighlightedWidget() == null){
                return;
            }
            edit.showEditPopup(workspace);
            //workspace.repaint();
        });
        this.add(edit.button);

//        Button addANDButton = new Button("And Gate");
//        addANDButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateAND");
//        });
//        this.add(addANDButton);

//        Button testingSave = new Button("S(Test)");
//        testingSave.addActionListener((evt) -> {
//            workspace.mainCircuit.save("Testing");
//        });
//        this.add(testingSave);
//
//        Button testingLoad = new Button("L(Test)");
//        testingLoad.addActionListener((evt) -> {
//            workspace.loadMain("Testing");
//        });
//        this.add(testingLoad);
//
//        Button testingLoadSub = new Button("LS(Test)");
//        testingLoadSub.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("Circuit");
//            WidgetFactory.getInstance().setCircuitName("Testing");
//        });
//        this.add(testingLoadSub);
    }

    /**
    public void addTaskBarListener() {
        this.addPointerPressedListener(evt -> {
            this.x = evt.getX();
            this.y = evt.getY();
            Log.p("X and Y");
            Image current = ImageArray.get(0);
            current.rotate90Degrees(true);
            this.repaint();
        });
    }
**/
    /**public void paint(Graphics g){
        if (ImageArray.size() > 0) {
            g.drawImage(ImageArray.get(0), x, y);
        }
    }**/

    /**
     *     public void paint(Graphics g){
     *         if (ViewMenu.ImageArray.size() > 0) {
     *             g.drawImage(ViewMenu.ImageArray.get(0), ViewMenu.x, ViewMenu.y);
     *         }
     *     }
     */
}
