package org.ecs160.a2;
import com.codename1.ui.*;
import org.ecs160.a2.Component.*;
import org.ecs160.a2.Component.Gate.*;

import java.util.ArrayList;

public class ViewWorkspace extends Container {
    private ArrayList<Selectable> testings;
    private Selectable highlighted;

    public ViewWorkspace() {
        super();
        testings = new ArrayList<Selectable>();
        this.testings.add(new Testing(200, 200));
        this.testings.add(new Testing(400, 700));

        this.addPointerPressedListener(evt -> {
            Selectable clicked = getWidget(evt.getX(), evt.getY());
            if (highlighted == null && clicked == null) {
                return;
            }
            if (highlighted != null && clicked != null) {
                if (clicked != highlighted) {
                    highlighted.flipSelected();
                    clicked.flipSelected();
                    highlighted = clicked;
                } else {
                    highlighted.flipSelected();
                    highlighted = null;
                }
            } else if (highlighted == null){
                this.highlighted = clicked;
                highlighted.flipSelected();
            } else {
                // clicked is null
                highlighted.setCoordinates(evt.getX(), evt.getY()-this.getAbsoluteY());
            }
            this.repaint();
        });

        Switch s1 = new Switch(20, 20);
        Switch s2 = new Switch(20, 20);
        Switch s3 = new Switch(20, 20);
        GateAND a1 = new GateAND(40, 40);
        GateOR o1 = new GateOR(60,70);
        Led l = new Led(88, 88);

        connect(s1.getNodeOutput(), a1.getNodeInput(0));
        connect(s2.getNodeOutput(), a1.getNodeInput(1));
        connect(a1.getNodeOutput(), o1.getNodeInput(0));
        connect(s3.getNodeOutput(), o1.getNodeInput(1));
        connect(o1.getNodeOutput(), l.getNodeInput());
        s1.powerSwitch();
        s2.powerSwitch();
        l.testing();
    }

    @Override
    public void paint(Graphics g){
        for (Selectable component : testings) {
            component.draw(g);
        }
    }

    public void connect(NodeOutput output, NodeInput input) {
        output.connect(input);
        input.connect(output);
    }

    public void disconnect(NodeOutput output, NodeInput input) {
        output.disconnect(input);
        input.disconnect();
    }

    public Selectable getWidget(int x, int y) {
        for (Selectable jerk : testings) {
            if (jerk.hitBoxHas(x, y)) {
                return jerk;
            }
        }
        return null;
    }
}
