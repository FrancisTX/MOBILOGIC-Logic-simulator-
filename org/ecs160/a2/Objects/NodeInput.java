package org.ecs160.a2.Objects;

import org.ecs160.a2.Objects.Interface.Node;
import org.ecs160.a2.Objects.Interface.Widget;

public class NodeInput extends Node {
    private NodeOutput prev; // previous output connected

    public NodeInput(Widget widget, int x, int y) {
        super(widget, x, y);
        prev = null;
    }

    public void update() {
        this.getWidget().update();
    }

    public boolean getVal() {
        if (prev == null) {
            return false;
        } else {
            return prev.getVal();
        }
    }
    public void connect(NodeOutput output) {
        prev = output;
    }
    public void disconnect() {
        prev = null;
    }
    public boolean connected() {
        return prev != null;
    }
}
