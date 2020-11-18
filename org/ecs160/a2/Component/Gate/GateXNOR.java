package org.ecs160.a2.Component.Gate;

import org.ecs160.a2.Component.NodeInput;

public class GateXNOR extends LogicGate {
    public GateXNOR(int x, int y) {
        super(x, y);
    }

    @Override
    public int getMinInputSize() {
        return NORMALGATE_INPUT_MINSIZE;
    }

    @Override
    public int getMaxInputSize() {
        return NORMALGATE_INPUT_MAXSIZE;
    }

    @Override
    public boolean getComputedOutput() {
        int counter = 0;
        for (NodeInput input : inputs) {
            if (input.getVal()) {
                counter += 1;
            }
        }
        return counter % 2 == 0;
    }
}
