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
    public int perform(List<Integer> program, int position) {
        parameterManager.set(ParameterManager.POSITION, position + 1, inputs.get(), program);
        return position + 2;
    }

    @Override
    public int opCode() {
        return 3;
    }
}
