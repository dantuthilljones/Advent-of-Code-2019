package computer.operations;

import computer.ParameterManager;
import computer.io.ComputerInput;

import java.util.List;

public class Input implements Operation {

    private final ParameterManager parameterManager;
    private final ComputerInput inputs;

    public Input(ParameterManager parameterManager, ComputerInput computerImput) {
        this.parameterManager = parameterManager;
        this.inputs = computerImput;
    }

    @Override
    public int perform(List<Long> program, int position) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        parameterManager.set(paramModes[0], position + 1, inputs.get(), program);
        return position + 2;
    }

    @Override
    public int opCode() {
        return 3;
    }
}
