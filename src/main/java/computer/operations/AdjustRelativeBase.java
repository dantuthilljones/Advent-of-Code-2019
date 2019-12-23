package computer.operations;

import computer.ParameterManager;

import java.util.List;

public class AdjustRelativeBase implements Operation {

    private final ParameterManager parameterManager;

    public AdjustRelativeBase(ParameterManager parameterManager) {
        this.parameterManager = parameterManager;
    }

    @Override
    public int perform(List<Long> program, int position) {
        int[] paramModes = parameterManager.getParamModes(program.get(position));
        long baseAdjustment = parameterManager.get(paramModes[0], position +1, program);
        parameterManager.adjustRelativeBase(baseAdjustment);
        return position + 2;
    }

    @Override
    public int opCode() {
        return 9;
    }
}
