package org.ecs160.a2.Objects;

import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.Selectable;
import org.ecs160.a2.Utilities.Config;

public class Testing extends Selectable {
    private int height;
    private int width;
    public Testing(int x, int y) {
        super(x, y, 300, 300);
    }

    public void draw(Graphics g) {
        int color = selectStatus ?
                Config.getInstance().selectedLogicGateColor :
                Config.getInstance().unselectedLogicGateColor;
        g.setColor(color);
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
    }
}

