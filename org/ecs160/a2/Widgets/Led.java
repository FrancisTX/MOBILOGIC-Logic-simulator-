package org.ecs160.a2.Widgets;

public class Led extends Widget {
    public Led(int x, int y) {
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
        return this.inputs.get(0).getVal();
    }
    public NodeInput getNodeInput() { return this.inputs.get(0); }

    public void testing() {
        System.out.println(this.getComputedOutput());
    }
}
