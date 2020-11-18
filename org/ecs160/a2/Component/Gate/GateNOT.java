package org.ecs160.a2.Component.Gate;

public class GateNOT extends LogicGate {
    public GateNOT(int x, int y) {
        super(x, y);
    }

    @Override
    public int getMinInputSize() {
        return 1;
    }

    @Override
    public int getMaxInputSize() {
        return 1;
    }

    @Override
    public boolean getComputedOutput() {
        return !inputs.get(0).getVal();
    }
}
