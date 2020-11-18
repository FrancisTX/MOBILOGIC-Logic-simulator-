package org.ecs160.a2.Objects;
import com.codename1.ui.Graphics;
import org.ecs160.a2.Objects.Interface.Node;
import org.ecs160.a2.Objects.Interface.Selectable;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Objects.Interface.LogicGate;

import java.util.ArrayList;

public class Circuit extends Widget {
    private ArrayList<Circuit> subCircuits;
    private ArrayList<Switch> switches;
    private ArrayList<Led> leds;
    private ArrayList<LogicGate> gates;
    private boolean isMainCircuit;

    private int indexOfCalcOutput;

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
        };

    }

    @Override
    public boolean getComputedOutput() {
        return leds.get(indexOfCalcOutput).getComputedOutput();
    }
    @Override
    public void update() {
        if (isMainCircuit) return;
        // TODO: Implement this
        // multiple outputs update
        for (int i = 0; i < outputs.size(); i++) {
            indexOfCalcOutput = i;
            outputs.get(i).update(getComputedOutput());
        }
        indexOfCalcOutput = 0;
    }

    @Override
    public int getMinInputsNum() { return 0; }

    @Override
    public int getMaxInputsNum() { return 7; }

    @Override
    public int getMinOutputNum() { return 0; }

    public ArrayList<Node> getAllNodes() {
        ArrayList<Node> all = new ArrayList<>();
        // TODO: Implement this
        return all;
    }

    @Override
    public void draw(Graphics g) {

    }
}
