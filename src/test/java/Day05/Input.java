package Day05;

import java.util.List;
import java.util.Queue;

public class Input implements Operation {

    private final ParameterManager parameterManager;

    public Input(ParameterManager parameterManager) {
        this.parameterManager = parameterManager;
    }

    @Override
    public int perform(List<Integer> program, int position, Queue<Integer> inputs, List<Integer> outputs) {
        parameterManager.set(ParameterManager.POSITION, position + 1, inputs.remove(), program);
        return position + 2;
    }

    @Override
    public int opCode() {
        return 3;
    }
}
