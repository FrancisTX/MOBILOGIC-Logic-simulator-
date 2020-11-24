package org.ecs160.a2.Objects;
import com.codename1.io.Externalizable;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.StorageManager.StorageManager;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Objects.Interface.LogicGate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Circuit extends Widget {
    private ArrayList<Circuit> subCircuits;
    private ArrayList<Switch> switches;
    private ArrayList<Led> leds;
    private ArrayList<LogicGate> gates;
    private boolean isMainCircuit;

    public Circuit(int x, int y) {
        super(x, y,
                Config.getInstance().subCircuitWidth,
                Config.getInstance().subCircuitHeight);
        this.subCircuits = new ArrayList<Circuit>();
        this.switches = new ArrayList<Switch>();
        this.leds = new ArrayList<Led>();
        this.gates = new ArrayList<LogicGate>();
        this.isMainCircuit = true;
    }

    public Circuit(int x, int y, Circuit circuit, boolean isMainCircuit) {
        super(x, y,
                Config.getInstance().subCircuitWidth,
                Config.getInstance().subCircuitHeight);
        this.subCircuits = circuit.subCircuits;
        this.switches = circuit.switches;
        this.leds = circuit.leds;
        this.gates = circuit.gates;
        this.isMainCircuit = isMainCircuit;
        if (!isMainCircuit) {
            populateInput(this.switches.size());
            populateOutput(this.leds.size());
        }
        super.setCoordinates(x, y);
    }

    @Override
    public void update() {
        if (isMainCircuit) return;
        // TODO: Implement this
        for (int i = 0; i < switches.size(); i++)
            switches.get(i).update(inputs.get(i).getVal());
        for (int i = 0; i < outputs.size(); i++)
            outputs.get(i).update(leds.get(i).getComputedOutput());
    }

    @Override // DUMMY FUNCTION, DON'T TOUCH, thanks
    public boolean getComputedOutput() { return false; }
    @Override
    public int getMinInputsNum() { return 0; }
    @Override
    public int getMaxInputsNum() { return 7; }
    @Override
    public int getMinOutputNum() { return 0; }

    public void save(String circuitName) {
        StorageManager.getInstance().save(circuitName, this);
    }

    public void load(String circuitName, int x, int y) {
        Circuit subCircuit = StorageManager.getInstance().load(circuitName);
        if (subCircuit == null) return;
        System.out.println("Found Circuit: ".concat(circuitName));
        subCircuits.add(new Circuit(x, y, subCircuit, false));
    }

    public void add(Widget item) {
        if (item instanceof Circuit) {
            subCircuits.add((Circuit) item);
        } else if (item instanceof Switch) {
            switches.add((Switch) item);
        } else if (item instanceof Led) {
            leds.add((Led)item);
        } else if (item instanceof LogicGate) {
            gates.add((LogicGate)item);
        }
    }

    public void remove(Widget item) {
        if (item instanceof Circuit) {
            subCircuits.remove(item);
        } else if (item instanceof Switch) {
            switches.remove(item);
        } else if (item instanceof Led) {
            leds.remove(item);
        } else if (item instanceof LogicGate) {
            gates.remove(item);
        }
    }

    public ArrayList<Circuit> getSubCircuits() {return subCircuits;}
    public ArrayList<Switch> getSwitches() {return switches;}
    public ArrayList<Led> getLeds() {return leds;}
    public ArrayList<LogicGate> getGates() {return gates;}
    public ArrayList<Widget> getAllWidgets() {
        ArrayList<Widget> all = new ArrayList<>(subCircuits);
        all.addAll(switches);
        all.addAll(leds);
        all.addAll(gates);
        return all;
    }

    @Override
    public void draw(Graphics g) {
        // Draw in sub-circuit Mode
        int color = selectStatus ?
                Config.getInstance().selectedWidgetColor :
                Config.getInstance().unselectedWidgetColor;
        g.setColor(color);
        char[] data = {'S', 'U', 'B'};
        g.drawChars(data, 0, 3, x + getWidth() / 5, y + getHeight() / 5);
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
        drawNodes(g);
    }
}
