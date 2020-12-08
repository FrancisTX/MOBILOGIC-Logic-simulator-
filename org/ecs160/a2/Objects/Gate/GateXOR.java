package org.ecs160.a2.Objects.Gate;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Utilities.Config;

public class GateXOR extends LogicGate {
    public int x, y;
    public Image Gate = Config.GateXOR;

    public GateXOR(){
        super();
    }

    public GateXOR(int x, int y) {
        super(x, y);
    }
    public String getType() { return "GateXOR"; }

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
        int counter = 0;
        for (NodeInput input : inputs) {
            if (input.getVal()) {
                counter += 1;
            }
        }
        return counter % 2 == 1;
    }

    @Override
    public void draw(Graphics g) {
        int color = selectStatus ?
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(color);
        // TODO: Implement Gate Drawing
        //g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
        g.drawArc(this.x, this.y-getHeight()/2, this.getWidth(), this.getHeight()/2, 0,-180); //top-most arc
        g.drawArc(this.x, this.y-getHeight()/3, this.getWidth(), this.getHeight()/2, 0,-180); //top arc
        g.drawArc(this.x, this.y - getHeight()- (int)(getHeight()*(1/1.7)), this.getWidth(), this.getHeight()+ (int)(getHeight()*1.765) , 0,-180); //bottom arc
        drawNodes(g);
    }
}
