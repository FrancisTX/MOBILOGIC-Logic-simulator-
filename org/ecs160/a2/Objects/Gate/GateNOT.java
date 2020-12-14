package org.ecs160.a2.Objects.Gate;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Utilities.Config;

public class GateNOT extends LogicGate {
    public GateNOT(int x, int y) {
        super(x, y);
    }
    public String getType() { return "GateNOT"; }
    public int delay;

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
        g.drawLine(this.x, this.y, this.x+getWidth(), this.y); //top
        g.drawLine(this.x, this.y, this.x+getWidth()/2, this.y+getHeight()); //left
        g.drawLine(this.x+getWidth(), this.y, this.x+getWidth()/2, this.y+getHeight()); //right
        g.drawArc(this.x+getWidth()/3, this.y+getHeight()-10, 50, 50, 0,360); //circle to display not association

        drawNodes(g);
    }
}
