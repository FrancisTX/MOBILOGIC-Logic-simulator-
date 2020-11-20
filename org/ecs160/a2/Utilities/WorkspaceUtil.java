package org.ecs160.a2.Utilities;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.Node;
import org.ecs160.a2.Objects.Interface.Selectable;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Objects.NodeOutput;
import org.ecs160.a2.Objects.SwitchButton;
import org.ecs160.a2.UI.Grid;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class WorkspaceUtil {
    private Selectable highlighted;
    private static final WorkspaceUtil instance = new WorkspaceUtil();
    private WorkspaceUtil() {}
    public static WorkspaceUtil getInstance() { return instance; }

    public void handleClick(Grid grid, int x, int y, ArrayList<Widget> widgets) {
        {
            Selectable clicked = getSelectable(widgets, x, y);
            if (highlighted == null && clicked == null) {
                return;
            }
            if (clicked instanceof SwitchButton) {
                handleClickSwitchButton((SwitchButton)clicked);
                return;
            }
            if (highlighted != null && clicked != null) {
                // if we click something when we have highlighted selectable
                if (clicked != highlighted) {
                    // two different selected component!
                    SimpleEntry<NodeInput, NodeOutput> pair = oneInputoneOutput(highlighted, clicked);
                    if (pair == null || feedBackDetected(highlighted, clicked)) {
                        // is not one input one output situation
                        // OR maybe we have feedback in the same widget
                        // switch hightlighted selectable
                        highlighted.flipSelected();
                        clicked.flipSelected();
                        highlighted = clicked;
                    } else {
                        // unselect current node
                        // then make connection
                        flipConnection((Node)highlighted, (Node)clicked);
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
                highlighted.setCoordinates(grid.convertCoordAbstoGrid(x, 'x'),
                        grid.convertCoordAbstoGrid(y, 'y'));
            }
        }
    }

    public void handleClickSwitchButton(SwitchButton clicked) {
        clicked.powerSwitch();
    }

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

    public void drawWire(Graphics g, Widget widget) {
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

    public Selectable getSelectable(ArrayList<Widget> widgets, int x, int y) {
        for (Widget widget : widgets) {
            if (widget.hitBoxHas(x, y)) {
                return widget;
            }
            for (Node node : widget.getAllNodes()) {
                if (node.hitBoxHas(x, y)) return node;
            }
        }
        return null;
    }


}
