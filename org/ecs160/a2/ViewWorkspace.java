package org.ecs160.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.*;
import org.ecs160.a2.Objects.Gate.GateAND;
import org.ecs160.a2.Objects.Interface.Node;
import org.ecs160.a2.Objects.Interface.Selectable;

import java.util.ArrayList;

public class ViewWorkspace extends Container {
    private ArrayList<Selectable> selectables;
    private Selectable highlighted;

    public ViewWorkspace() {
        super();
        selectables = new ArrayList<>();
        this.selectables.add(new Testing(200, 40));
        this.selectables.add(new Testing(400, 200));
        this.selectables.add(new GateAND(300, 600));
        this.addClickListener();
//        Switch s1 = new Switch(20, 20);
//        Switch s2 = new Switch(20, 20);
//        Switch s3 = new Switch(20, 20);
//        GateAND a1 = new GateAND(40, 40);
//        GateOR o1 = new GateOR(60,70);
//        Led l = new Led(88, 88);
//
//        connect(s1.getNodeOutput(), a1.getNodeInput(0));
//        connect(s2.getNodeOutput(), a1.getNodeInput(1));
//        connect(a1.getNodeOutput(), o1.getNodeInput(0));
//        connect(s3.getNodeOutput(), o1.getNodeInput(1));
//        connect(o1.getNodeOutput(), l.getNodeInput());
//        s1.powerSwitch();
//        s2.powerSwitch();
//        l.testing();
    }

    @Override
    public void paint(Graphics g){
        for (Selectable selectable : selectables) {
            selectable.draw(g);
        }
    }

    public void flipConnection(Node first, Node second) {
        NodeInput input;
        NodeOutput output;

        if (first instanceof NodeInput && second instanceof NodeOutput) {
            input = (NodeInput)first;
            output = (NodeOutput)second;
        } else if (second instanceof NodeInput && first instanceof NodeOutput) {
            input = (NodeInput)second;
            output = (NodeOutput)first;
        } else return;

        if (input.connected()) {
            // connected, disconnect now
            output.disconnect(input);
            input.disconnect();
        } else {
            // not connected, connect now
            output.connect(input);
            input.connect(output);
        }
    }

    public Selectable getSelectable(int x, int y) {
        for (Selectable component : selectables) {
            if (component.hitBoxHas(
                    x-getParent().getAbsoluteX(),
                    y-getParent().getAbsoluteY()))
            {
                return component;
            }
        }
        return null;
    }

    public boolean oneInputoneOutput(Selectable first, Selectable second) {
        return (first instanceof NodeInput && second instanceof NodeOutput) ||
                (first instanceof NodeOutput && second instanceof NodeInput);
    }

    public void addClickListener() {
        this.addPointerPressedListener(evt -> {
            Selectable clicked = getSelectable(evt.getX(), evt.getY());
            if (highlighted == null && clicked == null) {
                return;
            }
            if (highlighted != null && clicked != null) {
                // if we click something when we have highlighted selectable
                if (clicked != highlighted) {
                    // two different selected component!
                    if (oneInputoneOutput(highlighted, clicked)) {
                        // unselect current node
                        // then make connection
                        this.highlighted = null;
                        flipConnection((Node)highlighted, (Node)clicked);
                    } else {
                        // normal case switch hightlighted selectable
                        highlighted.flipSelected();
                        clicked.flipSelected();
                        highlighted = clicked;
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
                highlighted.setCoordinates(evt.getX()-getParent().getAbsoluteX(),
                        evt.getY()-getParent().getAbsoluteY());
            }
            this.repaint();
        });
    }
}
