package org.ecs160.a2.Objects.Interface;
import org.ecs160.a2.Utilities.Config;
import org.ecs160.a2.Objects.NodeInput;
import org.ecs160.a2.Utilities.WorkspaceUtil;


public abstract class LogicGate extends Widget {
    protected final int NORMALGATE_INPUT_MINSIZE = 2;
    protected final int NORMALGATE_INPUT_MAXSIZE = 5;

    public LogicGate(int x, int y) {
        super(x, y,
                Config.getInstance().logicGateWidth,
                Config.getInstance().logicGateHeight);
    }
    public abstract String getType();

    public void changeInputSize(int inputSize) {
        for (NodeInput input : inputs) {
            WorkspaceUtil.getInstance().disconnect(input, input.getConnectedOutput());
        }
        if (inputSize < getMinInputsNum() || inputSize > getMaxInputsNum() || inputSize == inputs.size()) {
            return; // input size cannot be smaller than 2
        }
        populateInput(inputSize);
    }

    public void changeDelayTime(int time){
        setDelay(time);
    }

    @Override
    public int getMinOutputNum() {
        return 1;
    }
}
