package org.ecs160.a2.Utilities;
import org.ecs160.a2.Objects.Gate.*;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Objects.Led;
import org.ecs160.a2.Objects.Switch;

public class WidgetFactory {
    private static final WidgetFactory instance = new WidgetFactory();
    private WidgetFactory() {}
    public static WidgetFactory getInstance() { return instance; }

    public Widget createWidget(String typeName, int x, int y) {
        switch (typeName) {
            case "GateAND":
                return new GateAND(x, y);
            case "GateOR":
                return new GateOR(x, y);
            case "GateNAND":
                return new GateNAND(x, y);
            case "GateNOR":
                return new GateNOR(x, y);
            case "GateNOT":
                return new GateNOT(x, y);
            case "GateXOR":
                return new GateXOR(x, y);
            case "GateXNOR":
                return new GateXNOR(x, y);
            case "Switch":
                return new Switch(x, y);
            case "Led":
                return new Led(x, y);
        }
        return null;
    }
}
