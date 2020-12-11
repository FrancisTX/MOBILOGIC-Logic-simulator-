package org.ecs160.a2.Objects;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Utilities.Config;

public class Led extends Widget {
    public Led(int x, int y) {
        super(x, y,
                Config.getInstance().ledWidth,
                Config.getInstance().ledHeight);
    }

    @Override
    public int getMinInputsNum() {
        return 1;
    }
    @Override
    public int getMaxInputsNum() {
        return 1;
    }

    @Override
    public int getMinOutputNum() {
        return 0;
    }

    @Override
    public boolean getComputedOutput() {
        return this.inputs.get(0).getVal();
    }
    public NodeInput getNodeInput() { return this.inputs.get(0); }

    private int calcIndicatorX(){
        return x + width / 2 - Config.getInstance().ledIndicatorWidth / 2;
    }
    private int calcIndicatorY(){
        return y + height / 2 - Config.getInstance().ledIndicatorHeight / 2;
    }

    @Override
    public void draw(Graphics g) {
        int borderColor = selectStatus ?
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(borderColor);
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
        drawNodes(g);

        int indicatorColor = this.getComputedOutput() ?
                Config.getInstance().ledIndicatorOnColor :
                Config.getInstance().ledIndicatorOffColor;
        g.setColor(indicatorColor);
        g.fillRect(calcIndicatorX(), calcIndicatorY(),
                Config.getInstance().ledIndicatorWidth,
                Config.getInstance().ledIndicatorHeight);

    }
}
