package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import org.ecs160.a2.Objects.Gate.GateAND;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
import com.codename1.ui.Command;
>>>>>>> parent of e3156b4... Merge branch 'master' into Sam
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.UI.TrashCan;

public class ViewMenu extends Container {
    private ViewWorkspace workspace;

    public ViewMenu(ViewWorkspace workspace) {
        super();
        this.workspace = workspace;

        this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        Button testingSaveButton = new Button("Save");
        testingSaveButton.addActionListener((evt) -> {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            workspace.mainCircuit.save("Testing");
        });
        this.add(testingSaveButton);

<<<<<<< HEAD
        this.add(new TrashCan(workspace));
=======
        Image canImg;
        String url = System.getProperty("user.dir") + "/src/Images/trash_can.jpg";
        try {
            canImg = Image.createImage("file:" + url);
            Button Trash = new Button(canImg);
=======
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
            TextField circuitName = new TextField("", "Circuit Name", 20, TextArea.ANY);
            Command enter = new Command("Ok");
            if(Dialog.show("Save the Circuit", circuitName, enter, new Command("Cancel")) == enter){
                workspace.mainCircuit.save(circuitName.getText());
            }
        });
        this.add(testingSaveButton);

        //Image canImg;
        //String url = System.getProperty("user.dir") + "/src/Images/trash_can.jpg";
        //try {
            //canImg = Image.createImage("file:" + url);
            Button Trash = new Button("trash");
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
            //now add a click listener that will remove the selected widget
            Trash.addActionListener(evt -> {
                workspace.removeHighlighted();
                workspace.repaint();
            });
            this.add(Trash);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        } catch (IOException e) {
            e.printStackTrace();
        }
>>>>>>> parent of e3156b4... Merge branch 'master' into Sam

        Button addANDButton = new Button("And Gate");
        addANDButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateAND");
        });
        this.add(addANDButton);

        Button addORButton = new Button("Or Gate");
        addORButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateOR");
        });
        this.add(addORButton);
=======
       // } catch (IOException e) {
       //     e.printStackTrace();
        //}

//        Button addANDButton = new Button("And Gate");
//        addANDButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateAND");
//        });
//        this.add(addANDButton);
//
//        Button addORButton = new Button("Or Gate");
//        addORButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateOR");
//        });
//        this.add(addORButton);

        this.add(new EditButton(workspace));

        Button testingSave = new Button("S(Test)");
        testingSave.addActionListener((evt) -> {
            workspace.mainCircuit.save("Testing");
        });
        //this.add(testingSave);

        Button testingLoad = new Button("L(Test)");
        testingLoad.addActionListener((evt) -> {
            workspace.loadMain("Testing");
        });
        //this.add(testingLoad);

=======
       // } catch (IOException e) {
       //     e.printStackTrace();
        //}

//        Button addANDButton = new Button("And Gate");
//        addANDButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateAND");
//        });
//        this.add(addANDButton);
//
//        Button addORButton = new Button("Or Gate");
//        addORButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateOR");
//        });
//        this.add(addORButton);

        this.add(new EditButton(workspace));

        Button testingSave = new Button("S(Test)");
        testingSave.addActionListener((evt) -> {
            workspace.mainCircuit.save("Testing");
        });
=======
       // } catch (IOException e) {
       //     e.printStackTrace();
        //}

//        Button addANDButton = new Button("And Gate");
//        addANDButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateAND");
//        });
//        this.add(addANDButton);
//
//        Button addORButton = new Button("Or Gate");
//        addORButton.addActionListener((evt) -> {
//            WorkspaceUtil.getInstance().setWidgetAddingStrategy("GateOR");
//        });
//        this.add(addORButton);

        this.add(new EditButton(workspace));

        Button testingSave = new Button("S(Test)");
        testingSave.addActionListener((evt) -> {
            workspace.mainCircuit.save("Testing");
        });
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
        //this.add(testingSave);

        Button testingLoad = new Button("L(Test)");
        testingLoad.addActionListener((evt) -> {
            workspace.loadMain("Testing");
        });
        //this.add(testingLoad);

<<<<<<< HEAD
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
        Button testingLoadSub = new Button("LS(Test)");
        testingLoadSub.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().setWidgetAddingStrategy("Circuit");
            WidgetFactory.getInstance().setCircuitName("Testing");
        });
        //this.add(testingLoadSub);
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
    }
}
