package org.ecs160.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.*;
import org.ecs160.a2.Objects.Interface.*;
import org.ecs160.a2.StorageManager.StorageManager;
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.UI.Grid;

public class ViewWorkspace extends Container {
    private final Grid grid = Grid.getInstance();
    public Circuit mainCircuit;
    private final WorkspaceUtil util = WorkspaceUtil.getInstance();
    private int x, y;
    public ViewWorkspace() {
        super();
        mainCircuit = new Circuit(0, 0, true);
        util.setMainCircuit(mainCircuit);

        this.addClickListener();
    }

    @Override
    public void paint(Graphics g){
        grid.draw(g);
        for (Widget widget : mainCircuit.getAllWidgets()) {
            widget.draw(g);
            util.drawWire(g, widget);
        }
    }

    public void addClickListener() {
        this.addPointerPressedListener(evt -> {
            util.handleAdd(evt.getX()-getParent().getAbsoluteX(),
                    evt.getY()-getParent().getAbsoluteY());
            util.handleClick(
                    evt.getX()-getParent().getAbsoluteX(),
                    evt.getY()-getParent().getAbsoluteY());
            this.repaint();
        });
    }

    public void removeHighlighted() {
        mainCircuit.remove(util.getHighlightedWidget());
        util.resetHighlighted();
    }

    public void loadMain(String circuitName) {
        util.resetHighlighted();
        this.mainCircuit = StorageManager.getInstance().loadMain(circuitName);
        util.setMainCircuit(this.mainCircuit);
        repaint();
    }

    public void addTaskBarListener() {
        this.addPointerPressedListener(evt -> {
            this.x = evt.getX();
            this.y = evt.getY();
        });
    }
}
