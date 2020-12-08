package org.ecs160.a2.StorageManager;

import com.codename1.io.Storage;
import org.ecs160.a2.Objects.Circuit;

public class StorageManager {
    private static final StorageManager instance = new StorageManager();
    public static StorageManager getInstance() { return instance; }
    private StorageManager() {}

    public void save(String circuitName,Circuit circuit) {
        CircuitSavable savable = new CircuitSavable(circuit);
        Storage.getInstance().writeObject(circuitName, savable);
    }

    public Circuit load(String circuitName) {
        CircuitSavable savable = (CircuitSavable)Storage.getInstance().readObject(circuitName);
        if (savable == null) return null;
        return savable.extractCircuit();
    }
}
