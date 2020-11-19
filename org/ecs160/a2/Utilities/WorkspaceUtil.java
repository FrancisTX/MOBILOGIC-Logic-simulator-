package org.ecs160.a2.Utilities;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.Node;
import org.ecs160.a2.Objects.Interface.Selectable;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Objects.NodeOutput;

import java.util.AbstractMap.SimpleEntry;

public class WorkspaceUtil {
    private static final WorkspaceUtil instance = new WorkspaceUtil();
    private WorkspaceUtil() {}
    public static WorkspaceUtil getInstance() { return instance; }

    public boolean feedBackDetected(Selectable first, Selectable second) {
        SimpleEntry<NodeInput, NodeOutput> pair = oneInputoneOutput(first, second);
        if (pair == null) return false;
        NodeInput input = pair.getKey();
        NodeOutput output = pair.getValue();
        Widget widget = input.getWidget();
        for (NodeOutput node : widget.getAllOutputNodes()) {
            if (node == output) return true;
        }
        return false;
    }

    public SimpleEntry<NodeInput, NodeOutput> oneInputoneOutput(Selectable first, Selectable second) {
        if (first instanceof NodeInput && second instanceof NodeOutput)
            return new SimpleEntry<>((NodeInput) first, (NodeOutput) second);
        if (second instanceof NodeInput && first instanceof NodeOutput)
            return new SimpleEntry<>((NodeInput) second, (NodeOutput) first);
        return null;
    }

    public void flipConnection(Node first, Node second) {
        SimpleEntry<NodeInput, NodeOutput> pair = oneInputoneOutput(first, second);
        if (pair == null) return;
        NodeInput input = pair.getKey();
        NodeOutput output = pair.getValue();
        if (input.connected()) {
            // connected, disconnect now
            // System.out.println("disconnect");
            output.disconnect(input);
            input.disconnect();
        } else {
            // not connected, connect now
            // System.out.println("connect");
            output.connect(input);
            input.connect(output);
        }
    }

    public void drawWire(Graphics g, Widget widget, int parentX, int parentY) {
        for (NodeInput input : widget.getAllInputNodes()) {
            NodeOutput output = input.getConnectedOutput();
            if (output == null) continue;
            int color = output.getVal() ?
                    Config.getInstance().wireOnColor :
                    Config.getInstance().wireOffColor;
            g.setColor(color);
            g.drawLine(input.getX()+Config.getInstance().nodeRadius,
                    input.getY(),
                    output.getX()+Config.getInstance().nodeRadius,
                    output.getY());
        }
    }
}
