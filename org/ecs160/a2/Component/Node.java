package org.ecs160.a2.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.GeneralPath;
import org.ecs160.a2.Utilities.Config;

public class Node extends Selectable {
    GeneralPath p = new GeneralPath();
    private final Widget widget;

    public Node(Widget widget, int x, int y) {
        super(x, y,
                Config.getInstance().nodeWidth,
                Config.getInstance().nodeHeight);
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }

    public void draw(Graphics g) {
        int color = selectStatus ?
                Config.getInstance().selectedNodeColor :
                Config.getInstance().unselectedNodeColor;
        g.setColor(color);
        p.moveTo(this.x,this.y);
        g.fillShape(p);
        g.fillRoundRect(this.x, this.y, getWidth(), getHeight(), getWidth(), getHeight());
    }
}
