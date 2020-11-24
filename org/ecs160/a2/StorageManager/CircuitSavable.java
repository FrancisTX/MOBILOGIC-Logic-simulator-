package org.ecs160.a2.StorageManager;
import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import org.ecs160.a2.Objects.*;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Utilities.WorkspaceUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;

public class CircuitSavable implements Externalizable {
    private Circuit circuit;
    public CircuitSavable(Circuit circuit) { this.circuit = circuit; }
    public Circuit extractCircuit() { return circuit; }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        ArrayList<CircuitSavable> savableCircuits =  new ArrayList<>();
        for (Circuit subCircuit : circuit.getSubCircuits())
            savableCircuits.add(new CircuitSavable(subCircuit));
        Util.writeObject(savableCircuits, out);

        out.writeInt(circuit.getSwitches().size());
        out.writeInt(circuit.getLeds().size());

        ArrayList<GateSavable> savableGates =  new ArrayList<>();
        for (LogicGate gate: circuit.getGates())
            savableGates.add(new GateSavable(gate));
        Util.writeObject(savableGates, out);

        // Start to write connectivity
        Util.writeObject(getConnectivityGraph(), out);
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        // TODO: Internalize
        circuit = new Circuit(Integer.MAX_VALUE, Integer.MAX_VALUE);
        ArrayList<CircuitSavable> savablesSubcircuits = (ArrayList<CircuitSavable>)Util.readObject(in);
        for (CircuitSavable savableCircuit : savablesSubcircuits)
            circuit.getSubCircuits().add(savableCircuit.extractCircuit());

        int numSwitches = in.readInt();
        for (int i = 0; i < numSwitches; i++)
            circuit.getSwitches().add(new Switch(Integer.MAX_VALUE, Integer.MAX_VALUE));
        int numLeds = in.readInt();
        for (int i = 0; i < numLeds; i++)
            circuit.getLeds().add(new Led(Integer.MAX_VALUE, Integer.MAX_VALUE));

        ArrayList<GateSavable> savableGates = (ArrayList<GateSavable>)Util.readObject(in);
        for (GateSavable savableGate : savableGates)
            circuit.getGates().add(savableGate.extractGate());

        HashMap<SimpleEntry<String, SimpleEntry<Integer, Integer>>,
                SimpleEntry<String, SimpleEntry<Integer, Integer>>> connectivity =
                (HashMap)Util.readObject(in);
        for (SimpleEntry<String, SimpleEntry<Integer, Integer>> inputAddress : connectivity.keySet()) {
            SimpleEntry<String, SimpleEntry<Integer, Integer>> outputAddress = connectivity.get(inputAddress);

            int inputJ = inputAddress.getValue().getValue();
            NodeInput input = getWidgetFrom(inputAddress).getAllInputNodes().get(inputJ);

            int outputJ = outputAddress.getValue().getValue();
            NodeOutput output = getWidgetFrom(outputAddress).getAllOutputNodes().get(outputJ);

            WorkspaceUtil.getInstance().connect(input, output);
        }
    }

    public Widget getWidgetFrom(SimpleEntry<String, SimpleEntry<Integer, Integer>> address) {
        int i = address.getValue().getKey();
        switch (address.getKey()) {
            case "Circuit":
                return circuit.getSubCircuits().get(i);
            case "Switch":
                return circuit.getSwitches().get(i);
            case "Led":
                return circuit.getLeds().get(i);
            case "LogicGate":
                return circuit.getGates().get(i);
            default:
                throw new IllegalArgumentException("Unknown Object Type");
        }
    }

    public HashMap<SimpleEntry<String, SimpleEntry<Integer, Integer>>,
            SimpleEntry<String, SimpleEntry<Integer, Integer>>> getConnectivityGraph() {
        HashMap<SimpleEntry<String, SimpleEntry<Integer, Integer>>,
                SimpleEntry<String, SimpleEntry<Integer, Integer>>> connectivity = new HashMap<>();
        // connectivity: NodeInput => NodeOutput
        ArrayList<Widget> widgets = circuit.getAllWidgets();
        for (int i = 0; i < widgets.size(); i++) {
            Widget keyWidget = widgets.get(i);
            String keyString;
            if (keyWidget instanceof Circuit)
                keyString = "Circuit";
            else if (keyWidget instanceof Switch)
                keyString = "Switch";
            else if (keyWidget instanceof Led)
                keyString = "Led";
            else if (keyWidget instanceof LogicGate)
                keyString = "LogicGate";
            else
                throw new IllegalArgumentException("Unknown Object Type");

            ArrayList<NodeInput> inputs = keyWidget.getAllInputNodes();
            for (int j = 0; j < inputs.size(); j++) {
                // key
                SimpleEntry<Integer, Integer> inputIndices = new SimpleEntry<>(i, j);
                SimpleEntry<String, SimpleEntry<Integer, Integer>> key =
                        new SimpleEntry<>(keyString, inputIndices);
                // value
                NodeInput input = inputs.get(j);
                if (!input.connected())
                    continue;
                NodeOutput output = input.getConnectedOutput();
                SimpleEntry<String, SimpleEntry<Integer, Integer>> value = getOutputAddress(output);
                if (value == null)
                    continue;
                connectivity.put(key, value);
            }
        }
        return connectivity;
    }

    public SimpleEntry<String, SimpleEntry<Integer, Integer>> getOutputAddress(NodeOutput target) {
        SimpleEntry<String, SimpleEntry<Integer, Integer>> address = null;
        SimpleEntry<Integer, Integer> indices = null;

        ArrayList<Circuit> subCircuits = circuit.getSubCircuits();
        ArrayList<Switch> switches = circuit.getSwitches();
        ArrayList<LogicGate> gates = circuit.getGates();

        for (int i = 0; i < subCircuits.size(); i++) {
            ArrayList<NodeOutput> outputNodes = subCircuits.get(i).getAllOutputNodes();
            for (int j = 0; j < outputNodes.size(); j++)
                if (target == outputNodes.get(j)) {
                    indices = new SimpleEntry<>(i, j);
                    address = new SimpleEntry<>("Circuit", indices);
                    return address;
                }
        }
        for (int i = 0; i < switches.size(); i++) {
            ArrayList<NodeOutput> outputNodes = switches.get(i).getAllOutputNodes();
            for (int j = 0; j < outputNodes.size(); j++)
                if (target == outputNodes.get(j)) {
                    indices = new SimpleEntry<>(i, j);
                    address = new SimpleEntry<>("Switch", indices);
                    return address;
                }
        }
        for (int i = 0; i < gates.size(); i++) {
            ArrayList<NodeOutput> outputNodes = gates.get(i).getAllOutputNodes();
            for (int j = 0; j < outputNodes.size(); j++)
                if (target == outputNodes.get(j)) {
                    indices = new SimpleEntry<>(i, j);
                    address = new SimpleEntry<>("LogicGate", indices);
                    return address;
                }
        }
        return null;
    }

    @Override
    public String getObjectId() {
        return "CircuitSavable";
    }
}
