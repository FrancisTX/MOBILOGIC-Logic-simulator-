package org.ecs160.a2.Objects.Gate;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Utilities.Config;

public class GateNAND extends LogicGate {
    public int x, y;
    public Image Gate = Config.GateNAND;

    public GateNAND(){
        super();
    }
    public GateNAND(int x, int y) {
        super(x, y);
    }
    public String getType() { return "GateNAND"; }

    @Override
    public int getMinInputsNum() {
        return NORMALGATE_INPUT_MINSIZE;
    }

    @Override
    public int getMaxInputsNum() {
        return NORMALGATE_INPUT_MAXSIZE;
    }

    @Override
    public boolean getComputedOutput() {
        boolean res = true;
        for (NodeInput input : inputs) {
            res &= input.getVal();
        }
        return !res;
    }

    @Override
    public void draw(Graphics g) {
        int color = selectStatus ?
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(color);
        // TODO: Implement Gate Drawing
        //g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
        g.drawLine(this.x, this.y, this.x+getWidth(), this.y); //top
        g.drawLine(this.x, this.y, this.x, this.y+getHeight()/2); //left
        g.drawLine(this.x+getWidth(), this.y, this.x+getWidth(), this.y+getHeight()/2); //right
        g.drawArc(this.x, this.y, this.getWidth(), this.getHeight(), 0,-180); //arc
        g.drawArc(this.x+getWidth()/3, this.y+getHeight()- 10, 50, 50, 0,360); //circle to display not association

        drawNodes(g);
    }
}
