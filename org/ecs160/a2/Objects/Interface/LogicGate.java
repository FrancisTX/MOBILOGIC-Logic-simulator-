package org.ecs160.a2.Objects.Interface;
import com.codename1.ui.*;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Objects.Interface.Widget;

import java.util.*;

public abstract class LogicGate extends Widget {
    protected final int NORMALGATE_INPUT_MINSIZE = 2;
    protected final int NORMALGATE_INPUT_MAXSIZE = 5;

    public LogicGate(int x, int y) {
        super(x, y,
                Config.getInstance().logicGateWidth,
                Config.getInstance().logicGateHeight);
    }

    public void drawNodes(Graphics g) {
        for (NodeInput input : inputs) {
            input.draw(g);
        }
        output.draw(g);
    }

    public NodeInput getNodeInput(int i) {
        if (i < 0 || i >= inputs.size()) {
            return null;
        }
        return inputs.get(i);
    }

    public void changeInputSize(int inputSize) {
        if (inputSize < getMinInputSize() || inputSize > getMaxInputSize() || inputSize == inputs.size()) {
            return; // input size cannot be smaller than 2
        }
        this.inputs = new ArrayList<NodeInput>();
        populateInput(inputSize);
    }
}
