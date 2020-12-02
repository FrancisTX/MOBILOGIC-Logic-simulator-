package org.ecs160.a2.Objects.Gate;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Utilities.Config;

public class GateNOR extends LogicGate {
    public GateNOR(int x, int y) {
        super(x, y);
    }
    public String getType() { return "GateNOR"; }

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
        boolean res = false;
        for (NodeInput input : inputs) {
            res |= input.getVal();
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
        g.drawArc(this.x, this.y-getHeight()/2, this.getWidth(), this.getHeight()/2, 0,-180); //top arc
        g.drawArc(this.x, this.y - getHeight()- (int)(getHeight()*(1/1.7)), this.getWidth(), this.getHeight()+ (int)(getHeight()*1.765) , 0,-180); //bottom arc
        g.drawArc(this.x+getWidth()/3, this.y+getHeight()- 10, 50, 50, 0,360); //circle to display not association
        drawNodes(g);
    }
}
