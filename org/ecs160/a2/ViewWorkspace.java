package org.ecs160.a2;

import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import java.util.AbstractMap.SimpleEntry;
import org.ecs160.a2.Objects.*;
import org.ecs160.a2.Objects.Gate.*;
import org.ecs160.a2.Objects.Interface.*;
import org.ecs160.a2.Utilities.WorkspaceUtil;
import org.ecs160.a2.UI.Grid;

import java.util.ArrayList;

public class ViewWorkspace extends Container {
    private final Grid grid = Grid.getInstance();
    public Circuit mainCircuit;
    private final WorkspaceUtil util = WorkspaceUtil.getInstance();

    public ViewWorkspace() {
        super();
        Util.register("Circuit", Circuit.class);
        mainCircuit = new Circuit(0, 0, true);
        this.addClickListener();

        mainCircuit.add(new GateAND(200, 550));
        mainCircuit.add(new GateOR(400, 900));
        mainCircuit.add(new Switch(100, 200));
        mainCircuit.add(new Switch(500, 200));
        mainCircuit.add(new Switch(900, 200));
        mainCircuit.add(new Led(500, 1500));

//        mainCircuit = new Circuit(0, 0, true);
//        mainCircuit.load("TESTING", 500, 900);
//        mainCircuit.add(new Switch(100, 200));
//        mainCircuit.add(new Switch(500, 200));
//        mainCircuit.add(new Switch(900, 200));
//        mainCircuit.add(new Led(500, 1500));
    }

    @Override
    public void paint(Graphics g){
        grid.draw(g);
        for (Widget widget : mainCircuit.getAllWidgets()) {
            widget.draw(g);
            util.drawWire(g, widget);
        }
    }

    public void addClickListener() {
        this.addPointerPressedListener(evt -> {
            util.handleClick(
                    evt.getX()-getParent().getAbsoluteX(),
                    evt.getY()-getParent().getAbsoluteY(),
                    mainCircuit.getAllWidgets());
            this.repaint();
        });
    }
}
