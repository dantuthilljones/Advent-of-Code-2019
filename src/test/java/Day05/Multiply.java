package Day05;

import java.util.List;
import java.util.Queue;

public class Multiply implements Operation {

    private final ParameterManager parameterManager;

    public Multiply(ParameterManager parameterManager) {
        this.parameterManager = parameterManager;
    }

    @Override
    public int perform(List<Integer> program, int position, Queue<Integer> inputs, List<Integer> outputs) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        int num1 = parameterManager.get(paramModes[0], position + 1, program);
        int num2 = parameterManager.get(paramModes[1], position + 2, program);
        int answer = num1 * num2;
        parameterManager.set(paramModes[2], position + 3, answer, program);
        return position + 4;
    }

    @Override
    public int opCode() {
        return 2;
    }
}
