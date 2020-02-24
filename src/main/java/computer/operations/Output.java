package computer.operations;

import computer.ParameterManager;
import computer.io.ComputerOutput;

import java.util.List;

public class Output implements Operation {

    private final ParameterManager parameterManager;
    private final computer.io.ComputerOutput computerOutput;

    public Output(ParameterManager parameterManager, computer.io.ComputerOutput computerOutput) {
        this.parameterManager = parameterManager;
        this.computerOutput = computerOutput;
    }

    @Override
    public int perform(List<Long> program, int position) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        long value = parameterManager.get(paramModes[0], position + 1, program);
        computerOutput.output(value);
        return position + 2;
    }

    @Override
    public int opCode() {
        return 4;
    }
}