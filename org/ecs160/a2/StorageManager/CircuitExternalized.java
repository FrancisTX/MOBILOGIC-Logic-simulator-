package org.ecs160.a2.StorageManager;
import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import org.ecs160.a2.Objects.Circuit;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.Switch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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
        

    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {

    }

    @Override
    public String getObjectId() {
        return "CircuitExternalized";
    }
}
