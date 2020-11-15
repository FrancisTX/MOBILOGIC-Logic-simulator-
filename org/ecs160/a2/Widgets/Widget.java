package org.ecs160.a2.Widgets;

import java.util.ArrayList;

public abstract class Widget extends Movable {
    protected ArrayList<NodeInput> inputs;
    protected final NodeOutput output;

    public Widget(int x, int y) {
        super(x, y);
        output = new NodeOutput(this);
        this.inputs = new ArrayList<NodeInput>();
        for (int i = 0; i < getMinInputSize(); i++) {
            this.inputs.add(new NodeInput(this));
        }
    }

    public abstract int getMinInputSize(); // 2 for normal gates, 1 otherwise
    public abstract int getMaxInputSize(); // 7 for normal gates, 1 otherwise

    public void update() {
        this.output.update(getComputedOutput());
    }

    public abstract boolean getComputedOutput(); // Main Logic Functions

    public NodeOutput getNodeOutput() {
        return output;
    }
}
