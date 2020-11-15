package org.ecs160.a2.Widgets;

import com.codename1.ui.Graphics;

public class Testing extends Movable {
    public Testing(int x, int y) {
        super(x, y);
    }

    public void draw(Graphics g) {
        g.setColor(0x0000ff);
        g.drawRect(getX()+5, getY()+5, 300, 300);
    }
}

