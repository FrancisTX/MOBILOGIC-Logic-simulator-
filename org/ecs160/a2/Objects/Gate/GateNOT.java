package org.ecs160.a2.Objects.Gate;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Utilities.Config;

public class GateNOT extends LogicGate {
    public int x, y;
    public Image Gate = Config.GateNOT;

    public GateNOT(){
        super();
    }

    public GateNOT(int x, int y) {
        super(x, y);
    }

    @Override
    public int getMinInputsNum() {
        return 1;
    }

    @Override
    public int getMaxInputsNum() {
        return 1;
    }

    @Override
    public boolean getComputedOutput() {
        return !inputs.get(0).getVal();
    }

    @Override
    public void draw(Graphics g) {
        int color = selectStatus ?
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(color);
        // TODO: Implement Gate Drawing
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
        drawNodes(g);
    }
}
