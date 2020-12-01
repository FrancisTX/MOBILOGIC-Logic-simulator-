package org.ecs160.a2.Objects;
import org.ecs160.a2.Objects.Interface.Node;
import org.ecs160.a2.Objects.Interface.Widget;

import java.util.ArrayList;

public class NodeOutput extends Node {
    // one output could map to multiple inputs
    private ArrayList<NodeInput> nextInputs = new ArrayList<NodeInput>();
    private boolean val;

    public NodeOutput(Widget widget, int x, int y) {
        super(widget, x, y);
        this.val = false;
    }
    public void update(boolean val) {
        this.val = val;
        for (NodeInput input: nextInputs) {
            input.update();
        }
    }
    public boolean getVal() {
        return val;
    }
    public void connect(NodeInput input) {
        disconnect(input);
        nextInputs.add(input);
    }
    public ArrayList<NodeInput> getAllConnectedInputs() { return this.nextInputs; }
    public void disconnect(NodeInput input) {
        while(nextInputs.remove(input)) { }
    }
    public void purgeAllInputs() { this.nextInputs = new ArrayList<>();}
}
