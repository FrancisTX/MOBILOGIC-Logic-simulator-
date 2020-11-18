package org.ecs160.a2.Component.Gate;

import org.ecs160.a2.Component.NodeInput;

public class GateAND extends LogicGate {

    public GateAND(int x, int y) {
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
        boolean res = true;
        for (NodeInput input : inputs) {
            res &= input.getVal();
        }
        return res;
    }
}
