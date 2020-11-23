package org.ecs160.a2.StorageManager;
import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.ecs160.a2.Objects.Circuit;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.Interface.Widget;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Objects.Switch;
import org.ecs160.a2.Objects.Led;

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
                SimpleEntry<Integer, Integer> indices = new SimpleEntry<>(i, j);
                SimpleEntry<String, SimpleEntry<Integer, Integer>> key =
                        new SimpleEntry<>(keyString, indices);
                // value
                // TODO: get indices of output Node
            }
        }
        return connectivity;
    }
}
