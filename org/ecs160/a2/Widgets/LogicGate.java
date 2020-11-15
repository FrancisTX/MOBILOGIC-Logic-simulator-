package org.ecs160.a2.Widgets;
import com.codename1.ui.*;
import java.util.*;

public abstract class LogicGate extends Widget {
    protected final int width = 20;
    protected final int height = 20;
    protected final int color = 0x0000ff;

    protected final int NORMALGATE_INPUT_MINSIZE = 2;
    protected final int NORMALGATE_INPUT_MAXSIZE = 7;

    public LogicGate(int x, int y) {
        super(x, y);
    }

    public void changeInputSize(int inputSize) {
        if (inputSize < getMinInputSize() || inputSize > getMaxInputSize() || inputSize == inputs.size()) {
            return; // input size cannot be smaller than 2
        }
        this.inputs = new ArrayList<NodeInput>();
        for (int i = 0; i < inputSize; i++) {
            this.inputs.add(new NodeInput(this));
        }
    }

    private int calcInputX(int index) {
        // TODO: Implement X COORDINATES FOR INPUT
        return this.getX();
    }
    private int calcInputY(int index) {
        // TODO: Implement Y COORDINATES FOR INPUT
        return this.getY();
    }
    private int calcOutputX(){
        // TODO: Implement X COORDINATES FOR OUTPUT
        return this.getX();
    }
    private int calcOutputY(){
        // TODO: Implement Y COORDINATES FOR OUTPUT
        return this.getY();
    }

    public void draw(Graphics g) {
        // TODO: Draw itself
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).draw(g, calcInputX(i), calcInputY(i));
        }
        output.draw(g, calcOutputX(), calcOutputY());
    }

    public NodeInput getNodeInput(int i) {
        if (i < 0 || i >= inputs.size()) {
            return null;
        }
        return inputs.get(i);
    }
}
