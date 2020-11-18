package org.ecs160.a2.Objects.Interface;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Objects.NodeOutput;
import org.ecs160.a2.Utilities.Config;

import java.util.ArrayList;

public abstract class Widget extends Selectable {
    protected ArrayList<NodeInput> inputs;
    protected final NodeOutput output;

    public Widget(int x, int y, int width, int height) {
        super(x, y, width, height);
        output = new NodeOutput(this, calcOutputX(), calcOutputY());
        this.inputs = new ArrayList<NodeInput>();
        populateInput(getMinInputSize());
        setCoordinates(x, y);
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
    public ArrayList<Node> getAllNodes() {
        ArrayList<Node> all = new ArrayList<>(inputs);
        all.add(output);
        return all;
    }

    @Override
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).setCoordinates(calcInputX(i), calcInputY());
        }
        output.setCoordinates(calcOutputX(), calcOutputY());
    };

    public void drawNodes(Graphics g) {
        for (NodeInput input : inputs) {
            input.draw(g);
        }
        output.draw(g);
    }

    public void populateInput(int size) {
        for (int i = 0; i < size; i++) {
            this.inputs.add(new NodeInput(this, calcInputX(i), calcInputY()));
        }
    }
    private int calcOutputX(){
        return this.x + width/2 - Config.getInstance().nodeWidth/2;
    }
    private int calcOutputY(){
        return this.getY() + this.height;
    }
    private int calcInputX(int index) {
        // TODO: Implement X COORDINATES FOR INPUT
        int gap = this.width / (this.inputs.size() + 1);
        return this.x + gap * (index + 1) - Config.getInstance().nodeWidth / 2;
    }
    private int calcInputY() {
        // TODO: Implement Y COORDINATES FOR INPUT
        return this.y - Config.getInstance().nodeHeight;
    }
}
