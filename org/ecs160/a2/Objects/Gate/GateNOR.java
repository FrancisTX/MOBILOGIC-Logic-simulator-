package org.ecs160.a2.Objects.Gate;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Utilities.Config;

public class GateNOR extends LogicGate {
    public int x, y;
    public Image Gate = Config.GateNOR;

    public GateNOR(){
        super();
    }
    public GateNOR(int x, int y) {
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
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
        drawNodes(g);
    }
}
