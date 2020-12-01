package org.ecs160.a2.Objects.Gate;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Utilities.Config;

public class GateOR extends LogicGate {

    public GateOR(int x, int y) {
        super(x, y);
    }
    public String getType() { return "GateOR"; }

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
        return res;
    }

    @Override
    public void draw(Graphics g) {
        int color = selectStatus ?
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(color);
        // TODO: Implement Gate Drawing
        char[] data = {'O', 'R'};

        g.drawArc(this.x, this.y-getHeight()/2, this.getWidth(), this.getHeight()/2, 0,-180); //top arc
        g.drawArc(this.x, this.y - getHeight()- (int)(getHeight()*(1/1.7)), this.getWidth(), this.getHeight()+ (int)(getHeight()*1.765) , 0,-180); //bottom arc
        drawNodes(g);
    }
}
