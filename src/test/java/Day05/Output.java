package Day05;

import java.util.List;
import java.util.Queue;

public class Output implements Operation {

    private final ParameterManager parameterManager;

    public Output(ParameterManager parameterManager) {
        this.parameterManager = parameterManager;
    }

    @Override
    public int perform(List<Integer> program, int position, Queue<Integer> inputs, List<Integer> outputs) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        int value = parameterManager.get(paramModes[0], position + 1, program);
        outputs.add(value);
        return position + 2;
    }

    @Override
    public int opCode() {
        return 4;
    }
}