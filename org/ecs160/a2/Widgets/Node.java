package org.ecs160.a2.Widgets;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.GeneralPath;

public class Node {
    GeneralPath p = new GeneralPath();
    private final Widget widget;

    public Node(Widget widget) {
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(0X52BE80);
        p.moveTo(x,y);
        g.fillShape(p);
        g.fillRoundRect(x, y, 20, 20, 20, 20);
    }
}
