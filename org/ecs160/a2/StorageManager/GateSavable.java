package org.ecs160.a2.StorageManager;
import com.codename1.io.Externalizable;
import org.ecs160.a2.Objects.Interface.LogicGate;
import org.ecs160.a2.Objects.Gate.*;

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
        switch (gateType) {
            case "GateAND":
                gate = new GateAND(Integer.MAX_VALUE, Integer.MAX_VALUE);
                break;
            case "GateNAND":
                gate = new GateNAND(Integer.MAX_VALUE, Integer.MAX_VALUE);
                break;
            case "GateNOR":
                gate = new GateNOR(Integer.MAX_VALUE, Integer.MAX_VALUE);
                break;
            case "GateNOT":
                gate = new GateNOT(Integer.MAX_VALUE, Integer.MAX_VALUE);
                break;
            case "GateOR":
                gate = new GateOR(Integer.MAX_VALUE, Integer.MAX_VALUE);
                break;
            case "GateXNOR":
                gate = new GateXNOR(Integer.MAX_VALUE, Integer.MAX_VALUE);
                break;
            case "GateXOR":
                gate = new GateXOR(Integer.MAX_VALUE, Integer.MAX_VALUE);
                break;
            default:
                System.out.println("Class ".concat(gateType).concat(" NOT FOUND"));
        }
        int numInputs = in.readInt();
        gate.changeInputSize(numInputs);
    }

    @Override
    public String getObjectId() {
        return "GateSavable";
    }
}
