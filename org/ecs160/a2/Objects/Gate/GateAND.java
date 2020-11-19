package org.ecs160.a2.Objects.Gate;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Utilities.Config;

public class GateAND extends LogicGate {

    public GateAND(int x, int y) {
        super(x, y);
    }

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
        return res;
    }
    @Override
    public void draw(Graphics g) {
        int color = selectStatus ?
                Config.getInstance().selectedLogicGateColor :
                Config.getInstance().unselectedLogicGateColor;
        g.setColor(color);
        // TODO: Implement Gate Drawing
        char[] data = {'A', 'N', 'D'};
        g.drawChars(data, 0, 3, x + getWidth() / 5, y + getHeight() / 5);
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
        drawNodes(g);
    }
}
