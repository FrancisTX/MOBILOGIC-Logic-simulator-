package org.ecs160.a2.Objects.Interface;
import com.codename1.ui.Image;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Objects.NodeInput;

import java.util.*;

public abstract class LogicGate extends Widget {
    protected final int NORMALGATE_INPUT_MINSIZE = 2;
    protected final int NORMALGATE_INPUT_MAXSIZE = 5;
    //public int x, y;
    public Image Gate;

    public LogicGate(){
        super();
    }

    public LogicGate(int x, int y) {
        super(x, y,
                Config.getInstance().logicGateWidth,
                Config.getInstance().logicGateHeight);
    }

    public NodeInput getNodeInput(int i) {
        if (i < 0 || i >= inputs.size()) {
            return null;
        }
        return inputs.get(i);
    }

    public void changeInputSize(int inputSize) {
        if (inputSize < getMinInputsNum() || inputSize > getMaxInputsNum() || inputSize == inputs.size()) {
            return; // input size cannot be smaller than 2
        }
        populateInput(inputSize);
    }

    @Override
    public int getMinOutputNum() {
        return 1;
    }
/**
    public int getImage() {
        return getGateImage();
    }

    public int getX() {
        return getGateX();
    }

    public int getY() {
        return getGateY();
    }
**/
}
