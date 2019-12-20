package computer.operations;

import computer.ParameterManager;

import java.util.List;
import java.util.Queue;

public class Equals implements Operation {

    private final ParameterManager parameterManager;

    public Equals(ParameterManager parameterManager) {
        this.parameterManager = parameterManager;
    }

    @Override
    public int perform(List<Integer> program, int position) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        int num1 = parameterManager.get(paramModes[0], position + 1, program);
        int num2 = parameterManager.get(paramModes[1], position + 2, program);

        if (num1 == num2) {
            parameterManager.set(paramModes[2], position + 3, 1, program);
        } else {
            parameterManager.set(paramModes[2], position + 3, 0, program);
        }
        return position + 4;
    }

    @Override
    public int opCode() {
        return 8;
    }
}