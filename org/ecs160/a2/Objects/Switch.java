package org.ecs160.a2.Objects;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.Selectable;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.UI.Grid;
import org.ecs160.a2.Utilities.Config;

import java.util.ArrayList;

public class Switch extends Widget {
    private final NodeOutput output;
    private SwitchButton button;

    public Switch(int x, int y) {
        super(x, y,
                Config.getInstance().switchWidth,
                Config.getInstance().switchHeight);
        output = getOneAndOnlyOutput();
        if (button == null) button = new SwitchButton(x+width/2, y+height/2, this);
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
        if (button == null) button = new SwitchButton(calcButtonX(), calcButtonY(), this);
        this.button.setCoordinates(calcButtonX(), calcButtonY());
    };

    @Override
    public ArrayList<Selectable> getAllAccessories() {
        ArrayList<Selectable> accessories = super.getAllAccessories();
        accessories.add(button);
        return accessories;
    }

    private int calcButtonX(){
        return x + width / 2 - Config.getInstance().switchButtonRadius;
    }
    private int calcButtonY(){
        return y + height / 2 - Config.getInstance().switchButtonRadius;
    }

    @Override
    public void draw(Graphics g) {
        int color = selectStatus ?
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(color);
        // TODO: Implement Gate Drawing
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
        button.draw(g);
        drawNodes(g);
    }
}
