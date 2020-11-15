package org.ecs160.a2;
import com.codename1.ui.*;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.layouts.*;
import org.ecs160.a2.Widgets.*;

public class ViewWorkspace extends Container {
    private final Testing testing;
    public ViewWorkspace() {
        super();
        this.testing = new Testing(200, 200);
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
//        s1.powerSwitch();
//        s3.powerSwitch();
//        l.testing();
    }

    @Override
    public void paint(Graphics g){
        this.testing.draw(g);
    }

    public void connect(NodeOutput output, NodeInput input) {
        output.connect(input);
        input.connect(output);
    }

    public void disconnect(NodeOutput output, NodeInput input) {
        output.disconnect(input);
        input.disconnect();
    }
}
