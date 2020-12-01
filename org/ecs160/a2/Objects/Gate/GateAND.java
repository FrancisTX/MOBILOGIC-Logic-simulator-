package org.ecs160.a2.Objects.Gate;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Utilities.Config;

public class GateAND extends LogicGate {

    public GateAND(int x, int y) {
        super(x, y);
    }
    public String getType() { return "GateAND"; }

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
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(color);
        // TODO: Implement Gate Drawing
        //char[] data = {'A', 'N', 'D'};
        //g.drawChars(data, 0, 3, x + getWidth() / 5, y + getHeight() / 5);
        //g.setColor(0xe3e3e3);
        //g.drawRect(this.x, this.y, this.getWidth(), this.getHeight()/2);

        g.drawLine(this.x, this.y, this.x+getWidth(), this.y); //top
        g.drawLine(this.x, this.y, this.x, this.y+getHeight()/2); //left
        g.drawLine(this.x+getWidth(), this.y, this.x+getWidth(), this.y+getHeight()/2); //right
        g.drawArc(this.x, this.y, this.getWidth(), this.getHeight(), 0,-180); //arc

        //g.fillArc(this.x, this.y, this.getWidth(), this.getHeight(), 180,180);
        //g.fillRect(this.x, this.y, this.getWidth(), this.getHeight()/2);

        drawNodes(g);
    }
}
