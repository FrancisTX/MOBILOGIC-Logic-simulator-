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
<<<<<<< HEAD
=======

    public Circuit loadMain(String circuitName) {
        CircuitSavable savable = (CircuitSavable)Storage.getInstance().readObject(circuitName);
        if (savable == null) return null;
        Circuit mainCircuit = savable.extractCircuit();
        HashMap<String, ArrayList<ArrayList<Integer>>> coordinates = savable.coordinates;

        ArrayList<ArrayList<Integer>> circuitPosPairs = coordinates.get("Circuit");
        for (int i = 0; i < circuitPosPairs.size(); i++) {
            ArrayList<Integer> pair = circuitPosPairs.get(i);
            mainCircuit.getSubCircuits().get(i).setCoordinates(pair.get(0), pair.get(1));
        }

        ArrayList<ArrayList<Integer>> switchPosPairs = coordinates.get("Switch");
        for (int i = 0; i < switchPosPairs.size(); i++) {
            ArrayList<Integer> pair = switchPosPairs.get(i);
            mainCircuit.getSwitches().get(i).setCoordinates(pair.get(0), pair.get(1));
        }

        ArrayList<ArrayList<Integer>> ledPosPairs = coordinates.get("Led");
        for (int i = 0; i < ledPosPairs.size(); i++) {
            ArrayList<Integer> pair = ledPosPairs.get(i);
            mainCircuit.getLeds().get(i).setCoordinates(pair.get(0), pair.get(1));
        }

        ArrayList<ArrayList<Integer>> gatesPosPairs = coordinates.get("LogicGate");
        for (int i = 0; i < gatesPosPairs.size(); i++) {
            ArrayList<Integer> pair = gatesPosPairs.get(i);
            mainCircuit.getGates().get(i).setCoordinates(pair.get(0), pair.get(1));
        }
        mainCircuit.setIsMain(true);
        return mainCircuit;
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
=======
>>>>>>> parent of cfcdb60... Merge remote-tracking branch 'origin/Cat' into Sam
}
