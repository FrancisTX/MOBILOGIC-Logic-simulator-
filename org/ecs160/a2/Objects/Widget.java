package org.ecs160.a2.Objects;

import java.util.ArrayList;

public abstract class Widget extends Selectable {
    protected ArrayList<NodeInput> inputs;
    protected final NodeOutput output;

    public Widget(int x, int y, int width, int height) {
        super(x, y, width, height);
        output = new NodeOutput(this, calcOutputX(), calcOutputY());
        this.inputs = new ArrayList<NodeInput>();
        populateInput(getMinInputSize());
    }
    public abstract boolean getComputedOutput(); // Main Logic Functions
    public abstract int getMinInputSize(); // 2 for normal gates, 1 otherwise
    public abstract int getMaxInputSize(); // 7 for normal gates, 1 otherwise

    public void update() {
        this.output.update(getComputedOutput());
    }
    public NodeOutput getNodeOutput() {
        return output;
    }

    @Override
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).setCoordinates(calcInputX(i), calcInputY(i));
        }
        output.setCoordinates(calcOutputX(), calcOutputY());
    };

    public void populateInput(int size) {
        for (int i = 0; i < size; i++) {
            this.inputs.add(new NodeInput(this, calcInputX(i), calcInputY(i)));
        }
    }
    private int calcOutputX(){
        return this.getX() + this.width;
    }
    private int calcOutputY(){
        return this.getY();
    }
    private int calcInputX(int index) {
        // TODO: Implement X COORDINATES FOR INPUT
        return this.getX();
    }
    private int calcInputY(int index) {
        // TODO: Implement Y COORDINATES FOR INPUT
        return this.getY();
    }
}
