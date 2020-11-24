package org.ecs160.a2.Utilities;
import org.ecs160.a2.Objects.Gate.*;
import org.ecs160.a2.Objects.Interface.Widget;

public class WidgetFactory {
    private static final WidgetFactory instance = new WidgetFactory();
    private WidgetFactory() {}
    public static WidgetFactory getInstance() { return instance; }

    public Widget createWidget(String typeName, int x, int y) {
        if (typeName.equals("GateAND"))
            return new GateAND(x, y);
        else if (typeName.equals("GateOR"))
            return new GateAND(x, y);
        return null;
    }
}
