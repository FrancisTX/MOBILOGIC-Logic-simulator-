package org.ecs160.a2.StorageManager;
import com.codename1.io.Externalizable;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Utilities.WidgetFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GateSavable implements Externalizable {
    private LogicGate gate;
    public GateSavable() {}
    public GateSavable(LogicGate gate) { this.gate = gate; }
    public LogicGate extractGate() { return this.gate; }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        out.writeUTF(gate.getType());
        out.writeInt(gate.getAllInputNodes().size());
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        String gateType = in.readUTF();
        if (!gateType.equals("GateAND")
                && !gateType.equals("GateNAND")
                && !gateType.equals("GateNOR")
                && !gateType.equals("GateNOT")
                && !gateType.equals("GateOR")
                && !gateType.equals("GateXNOR")
                && !gateType.equals("GateXOR")) {
            throw new IllegalArgumentException("Class ".concat(gateType).concat(" NOT FOUND"));
        }
        gate = (LogicGate) WidgetFactory.getInstance().createWidget(gateType, Integer.MAX_VALUE, Integer.MAX_VALUE);
        int numInputs = in.readInt();
        gate.changeInputSize(numInputs);
    }

    @Override
    public String getObjectId() {
        return "GateSavable";
    }
}
