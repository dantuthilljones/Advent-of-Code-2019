package computer.operations;

import computer.ParameterManager;

import java.util.List;

public class JumpIfFalse implements Operation {

    private final ParameterManager parameterManager;

    public JumpIfFalse(ParameterManager parameterManager) {
        this.parameterManager = parameterManager;
    }

    @Override
    public int perform(List<Long> program, int position) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        long num1 = parameterManager.get(paramModes[0], position + 1, program);
        long num2 = parameterManager.get(paramModes[1], position + 2, program);

        if (num1 == 0) {
            return (int) num2;
        } else {
            return position + 3;
        }
    }

    @Override
    public int opCode() {
        return 6;
    }
}