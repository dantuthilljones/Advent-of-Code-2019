package computer.operations;

import computer.ParameterManager;
import computer.io.ComputerOutput;

import java.util.List;
import java.util.Queue;

public class Output implements Operation {

    private final ParameterManager parameterManager;
    private final ComputerOutput computerOutput;

    public Output(ParameterManager parameterManager, ComputerOutput computerOutput) {
        this.parameterManager = parameterManager;
        this.computerOutput = computerOutput;
    }

    @Override
    public int perform(List<Integer> program, int position) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        int value = parameterManager.get(paramModes[0], position + 1, program);
        computerOutput.output(value);
        return position + 2;
    }

    @Override
    public int opCode() {
        return 4;
    }
}