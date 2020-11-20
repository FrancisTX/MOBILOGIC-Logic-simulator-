package org.ecs160.a2.Objects;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Utilities.Config;

public class Switch extends Widget {
    private final NodeOutput output;
    private final SwitchButton button = new SwitchButton(x+width/2, y+height/2, this);

    public Switch(int x, int y) {
        super(x, y,
                Config.getInstance().switchWidth,
                Config.getInstance().switchHeight);
        output = getOneAndOnlyOutput();
    }

    @Override
    public int getMinInputsNum() {
        return 0;
    }
    @Override
    public int getMaxInputsNum() {
        return 0;
    }
    @Override
    public int getMinOutputNum() {
        return 1;
    }

    @Override
    public boolean getComputedOutput() {
        return output.getVal();
    }
    public void powerSwitch() {
        output.update(!output.getVal());
    }
    public void update(boolean val) {
        output.update(val);
    }

    @Override
    public void setCoordinates(int x, int y) {
        super.setCoordinates(x, y);
        this.button.setCoordinates(x+width/2, y+height/2);
    };

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
