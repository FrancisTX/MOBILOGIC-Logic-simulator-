package org.ecs160.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import java.util.AbstractMap.SimpleEntry;
import org.ecs160.a2.Objects.*;
import org.ecs160.a2.Objects.Gate.*;
import org.ecs160.a2.Objects.Interface.*;
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.UI.Grid;

import java.util.ArrayList;

public class ViewWorkspace extends Container {
    private final Grid grid = Grid.getInstance();
    private ArrayList<Widget> widgets;
    private final WorkspaceUtil util = WorkspaceUtil.getInstance();

    public ViewWorkspace() {
        super();
        widgets = new ArrayList<>();
        this.addClickListener();

        this.widgets.add(new GateAND(200, 400));
        this.widgets.add(new GateOR(400, 800));
    }

    @Override
    public void paint(Graphics g){
        grid.draw(g);
        for (Widget widget : widgets) {
            widget.draw(g);
            util.drawWire(g, widget);
        }
    }

    public void addClickListener() {
        this.addPointerPressedListener(evt -> {
            util.handleClick(
                    evt.getX()-getParent().getAbsoluteX(),
                    evt.getY()-getParent().getAbsoluteY(),
                    this.widgets);
            this.repaint();
        });
    }
}