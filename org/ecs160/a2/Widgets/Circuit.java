package org.ecs160.a2.Widgets;
import java.util.ArrayList;

public class Circuit extends Movable {
    private ArrayList<Circuit> subCircuits;
    private ArrayList<Switch> switches;
    private ArrayList<Led> leds;
    private ArrayList<LogicGate> gates;
    private boolean isMainCircuit;

    public Circuit(int x, int y, Circuit circuit, boolean isMainCircuit) {
        super(x, y);
        this.subCircuits = circuit.subCircuits;
        this.switches = circuit.switches;
        this.leds = circuit.leds;
        this.gates = circuit.gates;
        this.isMainCircuit = isMainCircuit;
    }

    public void connectSwitch(NodeOutput output, Switch sw) {
        if (isMainCircuit) {
            // switch connection is hidden in main circuit
            return;
        }
        for (Switch _sw : switches) {
            if (sw != _sw) continue;
            output.connect(sw.getNodeInput());
            sw.getNodeInput().connect(output);
            return;
        }
    }

    public void connectLed(Led led, NodeInput input) {
        if (isMainCircuit) {
            // LED connection is hidden in main circuit
            return;
        }
        for (Led _led : leds) {
            if (led != _led) continue;
            led.getNodeOutput().connect(input);
            input.connect(led.getNodeOutput());
            return;
        }
    }
}
