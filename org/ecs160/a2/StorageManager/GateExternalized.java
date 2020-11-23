package org.ecs160.a2.StorageManager;
import com.codename1.io.Externalizable;
import com.codename1.io.Log;
import org.ecs160.a2.Objects.Circuit;
import org.ecs160.a2.Objects.Interface.LogicGate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GateExternalized implements Externalizable {
    private LogicGate gate;
    public GateExternalized(LogicGate gate) { this.gate = gate; }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {

    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {

    }

    @Override
    public String getObjectId() {
        return null;
    }
}
