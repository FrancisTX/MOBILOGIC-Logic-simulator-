package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import org.ecs160.a2.Objects.Gate.GateAND;
import org.ecs160.a2.Utilities.WorkspaceUtil;

public class ViewMenu extends Container {
    private ViewWorkspace workspace;

    public ViewMenu(ViewWorkspace workspace) {
        super();
        this.workspace = workspace;

        this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        Button testingSaveButton = new Button("Save");
        testingSaveButton.addActionListener((evt) -> {
            workspace.mainCircuit.save("Testing");
        });
        this.add(testingSaveButton);

        Button deleteButton = new Button("Trash");
        deleteButton.addActionListener((evt) -> {
            workspace.removeHighlighted();
            workspace.repaint();
        });
        this.add(deleteButton);

        Button addButton = new Button("And Gate");
        addButton.addActionListener((evt) -> {
            WorkspaceUtil.getInstance().flipAddMode();
            WorkspaceUtil.getInstance().setWidgetToAdd("GateAnd");
        });
        this.add(addButton);
    }
}
