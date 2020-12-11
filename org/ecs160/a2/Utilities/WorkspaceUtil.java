package org.ecs160.a2.Utilities;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Circuit;
import org.ecs160.a2.Objects.Interface.Node;
import org.ecs160.a2.Objects.Interface.Selectable;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Objects.NodeOutput;
import org.ecs160.a2.Objects.SwitchButton;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class WorkspaceUtil {
    private Circuit mainCircuit;
    private Selectable highlighted;
    private String widgetAddingType;
    private static final WorkspaceUtil instance = new WorkspaceUtil();
    private WorkspaceUtil() {}
    public static WorkspaceUtil getInstance() { return instance; }

    public void setMainCircuit(Circuit circuit) {
        mainCircuit = circuit;
    }
    public void handleAdd(int x, int y) {
        Selectable clicked = getSelectable(mainCircuit.getAllWidgets(), x, y);
        if (widgetAddingType == null || clicked != null) return;
        mainCircuit.add(WidgetFactory.getInstance().createWidget(widgetAddingType, x, y));
    }
    public void setWidgetAddingStrategy(String newItemType) {
        if (newItemType.equals(widgetAddingType)) {
            widgetAddingType = null;
        } else {
            widgetAddingType = newItemType;
        }
    }
    public String getWidgetAddingType() {
        return widgetAddingType;
    }

    public void handleClick(int x, int y) {
        {
            ArrayList<Widget> widgets = mainCircuit.getAllWidgets();
            if (widgetAddingType != null) return;
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
                highlighted.setCoordinates(x, y);
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
            output.disconnect(input);
            input.disconnect();
            input.update();
        } else {
            // not connected, connect now
            output.connect(input);
            input.connect(output);
            input.update();
        }
    }

    public void connect(NodeInput input, NodeOutput output) {
        if (input == null || output == null) return;
        if (input.connected()) return;
        output.connect(input);
        input.connect(output);
        input.update();
    }

    public void disconnect(NodeInput input, NodeOutput output) {
        if (input == null || output == null) return;
        if (!input.connected()) return;
        output.disconnect(input);
        input.disconnect();
        input.update();
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
            for (Selectable accessory : widget.getAllAccessories()) {
                if (accessory.hitBoxHas(x, y)) return accessory;
            }
            if (widget.hitBoxHas(x, y)) {
                return widget;
            }
        }
        return null;
    }

    public Widget getHighlightedWidget() {
        if (!(this.highlighted instanceof Widget))
            return null;
        return (Widget)this.highlighted;
    }

    public void resetHighlighted() {
        this.highlighted = null;
    }
}
