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
    private Grid grid;
    private ArrayList<Widget> widgets;
    private final WorkspaceUtil util = WorkspaceUtil.getInstance();

    public ViewWorkspace() {
        super();
        grid = new Grid();
        widgets = new ArrayList<>();
        this.addClickListener();

        this.widgets.add(new GateAND(grid.convertCoordAbstoGrid(200, 'x'), grid.convertCoordAbstoGrid(400, 'y')));
        this.widgets.add(new GateOR(grid.convertCoordAbstoGrid(400, 'x'), grid.convertCoordAbstoGrid(800, 'y')));
//        Switch s1 = new Switch(20, 20);
//        Switch s2 = new Switch(20, 20);
//        Switch s3 = new Switch(20, 20);
//        GateAND a1 = new GateAND(40, 40);
//        GateOR o1 = new GateOR(60,70);
//        Led l = new Led(88, 88);
//
//        util.flipConnection(s1.getOneAndOnlyOutput(), a1.getNodeInput(0));
//        util.flipConnection(s2.getOneAndOnlyOutput(), a1.getNodeInput(1));
//        util.flipConnection(a1.getOneAndOnlyOutput(), o1.getNodeInput(0));
//        util.flipConnection(s3.getOneAndOnlyOutput(), o1.getNodeInput(1));
//        util.flipConnection(o1.getOneAndOnlyOutput(), l.getNodeInput());
//        s1.powerSwitch();
//        s2.powerSwitch();
//        s1.powerSwitch();
//        l.testing();
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
                    grid,
                    evt.getX()-getParent().getAbsoluteX(),
                    evt.getY()-getParent().getAbsoluteY(),
                    this.widgets);
            this.repaint();
        });
        this.repaint();
    }
}
