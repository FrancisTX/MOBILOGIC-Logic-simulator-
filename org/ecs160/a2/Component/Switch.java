package org.ecs160.a2.Component;

import com.codename1.ui.Graphics;

public class Switch extends Widget {

    public Switch(int x, int y) {
        super(x, y, 100, 100);
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
        return output.getVal();
    }
    public NodeInput getNodeInput() { return this.inputs.get(0); }

    public void powerSwitch() {
        output.update(!output.getVal());
    }

    @Override
    public void draw(Graphics g) {
    }
}
