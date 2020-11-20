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
    private Selectable highlighted;
    private final WorkspaceUtil util = WorkspaceUtil.getInstance();

    public ViewWorkspace() {
        super();
        grid = new Grid();
        widgets = new ArrayList<>();
        this.addClickListener();

        this.widgets.add(new GateAND(grid.convertCoordAbstoGrid(200, 'x'), grid.convertCoordAbstoGrid(400, 'y'))));
        this.widgets.add(new GateOR(grid.convertCoordAbstoGrid(400, 'x'), grid.convertCoordAbstoGrid(800, 'y'))));
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
            Selectable clicked = util.getSelectable(this.widgets,
                    evt.getX()-getParent().getAbsoluteX(),
                    evt.getY()-getParent().getAbsoluteY());
            if (highlighted == null && clicked == null) {
                return;
            }
            if (highlighted != null && clicked != null) {
                // if we click something when we have highlighted selectable
                if (clicked != highlighted) {
                    // two different selected component!
                    SimpleEntry<NodeInput, NodeOutput> pair = util.oneInputoneOutput(highlighted, clicked);
                    if (pair == null || util.feedBackDetected(highlighted, clicked)) {
                        // is not one input one output situation
                        // OR maybe we have feedback in the same widget
                        // switch hightlighted selectable
                        highlighted.flipSelected();
                        clicked.flipSelected();
                        highlighted = clicked;
                    } else {
                        // unselect current node
                        // then make connection
                        util.flipConnection((Node)highlighted, (Node)clicked);
                        highlighted.flipSelected();
                        this.highlighted = null;
                    }
                } else {
                    // unselect a component
                    highlighted.flipSelected();
                    highlighted = null;
                }
            } else if (highlighted == null){
                // we don't have highlighted initially, but we click something
                this.highlighted = clicked;
                highlighted.flipSelected();
            } else {
                // we have highlighted, but click nothing
                if (highlighted instanceof Node) return;
                highlighted.setCoordinates(grid.convertCoordAbstoGrid(evt.getX()-getParent().getAbsoluteX(), 'x'),
                        grid.convertCoordAbstoGrid(evt.getY()-getParent().getAbsoluteY(), 'y'));
            }
            this.repaint();
        });
    }
}
