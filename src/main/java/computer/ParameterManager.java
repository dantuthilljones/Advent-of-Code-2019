package computer;

import java.util.List;

public class ParameterManager {

    public static int POSITION = 0;
    public static int IMMEDIATE = 1;
    public static int RELATIVE = 2;

    private int relativeBase = 0;

    public int[] getParamModes(long opcode) {
        return new int[] {
                (int) ((opcode / 100) % 10),
                (int) ((opcode / 1000) % 10),
                (int) ((opcode / 10000) % 10)
        };
    }

    public long get(int paramMode, int position, List<Long> program) {
        if (paramMode == POSITION) {
            return program.get(program.get(position).intValue());
        } else if (paramMode == IMMEDIATE) {
            return program.get(position);
        } else if (paramMode == RELATIVE) {
            return program.get(program.get(position).intValue() + relativeBase);
        }
        throw new RuntimeException("Unknown parameter mode " + paramMode);
    }

    public long set(int paramMode, int position, long value, List<Long> program) {
        if (paramMode == POSITION) {
            return program.set(program.get(position).intValue(), value);
        } else if (paramMode == IMMEDIATE) {
            throw new RuntimeException("Cannot set values in Immediate mode");
        } else if (paramMode == RELATIVE) {
            return program.set(program.get(position).intValue() + relativeBase, value);
        }
        throw new RuntimeException("Unknown parameter mode " + paramMode);
    }

    public void adjustRelativeBase(long by) {
        relativeBase += by;
    }
}
