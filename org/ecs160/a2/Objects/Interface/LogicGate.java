package org.ecs160.a2.Objects.Interface;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Objects.NodeInput;

import java.util.*;

public abstract class LogicGate extends Widget {
    protected final int NORMALGATE_INPUT_MINSIZE = 2;
    protected final int NORMALGATE_INPUT_MAXSIZE = 5;

    public LogicGate(int x, int y) {
        super(x, y,
                Config.getInstance().logicGateWidth,
                Config.getInstance().logicGateHeight);
    }
    public abstract String getType();

    public NodeInput getNodeInput(int i) {
        if (i < 0 || i >= inputs.size()) {
            return null;
        }
        return inputs.get(i);
    }

    public void changeInputSize(int inputSize) {
        if (inputSize < getMinInputsNum() || inputSize > getMaxInputsNum() || inputSize == inputs.size()) {
            return; // input size cannot be smaller than 2
        }
        populateInput(inputSize);
    }

    @Override
    public int getMinOutputNum() {
        return 1;
    }
}
