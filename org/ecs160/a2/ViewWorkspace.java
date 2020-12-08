package org.ecs160.a2;

import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import java.util.AbstractMap.SimpleEntry;

import com.codename1.ui.Image;
import org.ecs160.a2.Objects.*;
import org.ecs160.a2.Objects.Gate.*;
import org.ecs160.a2.Objects.Interface.*;
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.UI.Grid;

import javax.swing.text.View;
import java.util.ArrayList;

public class ViewWorkspace extends Container {
    private final Grid grid = Grid.getInstance();
    private ArrayList<Widget> widgets;
    private final WorkspaceUtil util = WorkspaceUtil.getInstance();
    private int x, y;
    public ViewWorkspace() {
        super();
        widgets = new ArrayList<>();
        this.addClickListener();
        this.addTaskBarListener();
        this.widgets.add(new GateAND(200, 550));
        this.widgets.add(new GateOR(400, 900));
        this.widgets.add(new Switch(100, 200));
        this.widgets.add(new Switch(500, 200));
        this.widgets.add(new Switch(900, 200));
        this.widgets.add(new Led(500, 1500));
    }

    @Override
    public void paint(Graphics g){
        grid.draw(g);
        for (Widget widget : widgets) {
            widget.draw(g);
            util.drawWire(g, widget);
        }
        /**
        if (ViewMenu.ImageArray.size() > 0) {
            for (int index = 0; index < ViewMenu.ImageArray.size(); index++) {
                LogicGate Gate = ViewMenu.ImageArray.get(index);
                
                g.drawImage(, x, y);
            }
        }**/
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

    public void addTaskBarListener() {
        this.addPointerPressedListener(evt -> {
            this.x = evt.getX();
            this.y = evt.getY();
            Log.p("X and Y");
        });
    }

    /**public void paint_gate(Graphics g){
        if (ViewMenu.ImageArray.size() > 0) {
            Image current = ViewMenu.ImageArray.get(0);
            current.rotate90Degrees(true);
            g.drawImage(current, this.x, this.y);
        }
    }**/
}
