package org.ecs160.a2.StorageManager;
import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.ecs160.a2.Objects.*;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.Interface.Widget;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;

public class CircuitExternalized implements Externalizable {
    private Circuit circuit;
    public CircuitExternalized(Circuit circuit) { this.circuit = circuit; }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        ArrayList<CircuitExternalized> subCircuitsExternalized =  new ArrayList<>();
        for (Circuit subCircuit : circuit.getSubCircuits())
            subCircuitsExternalized.add(new CircuitExternalized(subCircuit));
        Util.writeObject(subCircuitsExternalized, out);

        out.writeInt(circuit.getSwitches().size());
        out.writeInt(circuit.getLeds().size());

        ArrayList<GateExternalized> gatesExternalized =  new ArrayList<>();
        for (LogicGate gate: circuit.getGates())
            gatesExternalized.add(new GateExternalized(gate));
        // Start to write connectivity
        Util.writeObject(getConnectivityGraph(), out);
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        ArrayList<CircuitExternalized> subCircuitsExternalized = (ArrayList<CircuitExternalized>)Util.readObject(in);

    }

    @Override
    public String getObjectId() {
        return "CircuitExternalized";
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
            if (keyWidget instanceof Switch)
                keyString = "Switch";
            if (keyWidget instanceof Led)
                keyString = "Led";
            if (keyWidget instanceof LogicGate)
                keyString = "LogicGate";
            else
                throw new ValueException("Unknown Object Type");

            ArrayList<NodeInput> inputs = keyWidget.getAllInputNodes();
            for (int j = 0; j < inputs.size(); j++) {
                // key
                SimpleEntry<Integer, Integer> inputIndices = new SimpleEntry<>(i, j);
                SimpleEntry<String, SimpleEntry<Integer, Integer>> key =
                        new SimpleEntry<>(keyString, inputIndices);
                // value
                NodeInput input = inputs.get(j);
                NodeOutput output = input.getConnectedOutput();
                if (output == null)
                    continue;
                // TODO: get address of output Node
                SimpleEntry<String, SimpleEntry<Integer, Integer>> outputAddress = getOutputAddress(output);
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
}
