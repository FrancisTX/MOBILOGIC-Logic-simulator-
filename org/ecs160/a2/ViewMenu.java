package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import org.ecs160.a2.Objects.Gate.GateAND;
import com.codename1.ui.Command;
import org.ecs160.a2.Utilities.WorkspaceUtil;

public class ViewMenu extends Container {
    private ViewWorkspace workspace;

    public ViewMenu(ViewWorkspace workspace) {
        super();
        this.workspace = workspace;

        this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        Button testingSaveButton = new Button("Save");
        testingSaveButton.addActionListener((evt) -> {
            TextField circuitName = new TextField("", "Circuit Name", 20, TextArea.ANY);
            Command enter = new Command("Ok");
            if(Dialog.show("Save the Circuit", circuitName, enter, new Command("Cancel")) == enter){
                workspace.mainCircuit.save(circuitName.getText());
            }
        });
        this.add(testingSaveButton);

        Image canImg;
        String url = System.getProperty("user.dir") + "/src/Images/trash_can.jpg";
        try {
            canImg = Image.createImage("file:" + url);
            Button Trash = new Button(canImg);
            //now add a click listener that will remove the selected widget
            Trash.addActionListener(evt -> {
                workspace.removeHighlighted();
                workspace.repaint();
            });
            this.add(Trash);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    }
}
