package org.ecs160.a2.Objects.Interface;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Objects.NodeOutput;
import org.ecs160.a2.UI.Grid;
import org.ecs160.a2.Utilities.Config;

import java.util.ArrayList;

public abstract class Widget extends Selectable {
    protected ArrayList<NodeInput> inputs;
    protected ArrayList<NodeOutput> outputs;
    private final Grid grid = Grid.getInstance();

    public Widget(int x, int y, int width, int height) {
        super(
                Grid.getInstance().convertCoordAbstoGrid(x, 'x'),
                Grid.getInstance().convertCoordAbstoGrid(y, 'y'),
                width, height);
        populateInput(getMinInputsNum());
        populateOutput(getMinOutputNum());
        setCoordinates(x, y);
    }
    public abstract boolean getComputedOutput(); // Main Logic Functions
    public abstract int getMinInputsNum(); // 2 for normal gates, 1 otherwise
    public abstract int getMaxInputsNum(); // 7 for normal gates, 1 otherwise
    public abstract int getMinOutputNum();

    public void update() {
        for (NodeOutput output : outputs) {
            output.update(getComputedOutput());
        }
    }
    public NodeOutput getOneAndOnlyOutput() {
        return outputs.get(0);
    }
    public ArrayList<Selectable> getAllAccessories() {
        ArrayList<Selectable> all = new ArrayList<>(inputs);
        all.addAll(outputs);
        return all;
    }
    public ArrayList<NodeInput> getAllInputNodes() {
        return new ArrayList<>(inputs);
    }
    public ArrayList<NodeOutput> getAllOutputNodes() {
        return new ArrayList<>(outputs);
    }

    @Override
    public void setCoordinates(int x, int y) {
        this.x = Grid.getInstance().convertCoordAbstoGrid(x, 'x');
        this.y = Grid.getInstance().convertCoordAbstoGrid(y, 'y');
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).setCoordinates(calcInputX(i), calcInputY());
        }
        for (int i = 0; i < outputs.size(); i++) {
            outputs.get(i).setCoordinates(calcOutputX(i), calcOutputY());
        }
    };

    public void drawNodes(Graphics g) {
        for (NodeInput input : inputs) {
            input.draw(g);
        }
        for (NodeOutput output : outputs) {
            output.draw(g);
        }
    }

    public void populateInput(int size) {
        this.inputs = new ArrayList<NodeInput>();
        for (int i = 0; i < size; i++) {
            this.inputs.add(new NodeInput(this, calcInputX(i), calcInputY()));
        }
    }
    public void populateOutput(int size) {
        this.outputs = new ArrayList<NodeOutput>();
        for (int i = 0; i < size; i++) {
            this.outputs.add(new NodeOutput(this, calcOutputX(i), calcOutputY()));
        }
    }
    private int calcInputX(int index){
        int gap = this.width / (this.inputs.size() + 1);
        return this.x + gap * (index + 1) - Config.getInstance().nodeRadius;
    }
    private int calcOutputX(int index){
        int gap = this.width / (this.outputs.size() + 1);
        return this.x + gap * (index + 1) - Config.getInstance().nodeRadius;
    }
    private int calcOutputY(){
        return this.getY() + this.height;
    }
    private int calcInputY() {
        return this.y - Config.getInstance().nodeHeight;
    }
}
