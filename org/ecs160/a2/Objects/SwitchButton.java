package org.ecs160.a2.Objects;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.GeneralPath;
import org.ecs160.a2.Objects.Interface.Selectable;
import org.ecs160.a2.Utilities.Config;

public class SwitchButton extends Selectable {
    GeneralPath p = new GeneralPath();
    private final Switch sw;

    public SwitchButton(int x, int y, Switch sw) {
        super(x, y,
                Config.getInstance().switchButtonWidth,
                Config.getInstance().switchButtonHeight);
        this.sw = sw;
    }

    public void powerSwitch() {
        sw.powerSwitch();
    }

    @Override
    public void draw(Graphics g) {
        int color = this.sw.getComputedOutput() ?
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(color);
        p.moveTo(this.x,this.y);
        g.fillShape(p);
        g.fillRoundRect(this.x, this.y, getWidth(), getHeight(), getWidth(), getHeight());
    }
}
