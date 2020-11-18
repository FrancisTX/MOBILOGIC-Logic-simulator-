package org.ecs160.a2.Objects;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Utilities.Config;

public class Switch extends Widget {

    public Switch(int x, int y) {
        super(x, y,
                Config.getInstance().switchWidth,
                Config.getInstance().switchHeight);
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
