package Day05;

import java.util.List;
import java.util.Queue;

public class JumpIfFalse implements Operation {

    private final ParameterManager parameterManager;

    public JumpIfFalse(ParameterManager parameterManager) {
        this.parameterManager = parameterManager;
    }

    @Override
    public int perform(List<Integer> program, int position, Queue<Integer> inputs, List<Integer> outputs) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        int num1 = parameterManager.get(paramModes[0], position + 1, program);
        int num2 = parameterManager.get(paramModes[1], position + 2, program);

        if (num1 == 0) {
            return num2;
        } else {
            return position + 3;
        }
    }

    @Override
    public int opCode() {
        return 6;
    }
}