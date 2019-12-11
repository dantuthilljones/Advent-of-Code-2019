package Day05;

import java.util.List;

public class ParameterManager {

    public static int POSITION = 0;
    public static int IMMEDIATE = 1;

    public int[] getParamModes(int opcode) {
        return new int[] {
                (opcode / 100) % 10,
                (opcode / 1000) % 10,
                (opcode / 10000) % 10
        };
    }

    public int get(int paramMode, int position, List<Integer> program) {
        if (paramMode == POSITION) {
            return program.get(program.get(position));
        } else if (paramMode == IMMEDIATE) {
            return program.get(position);
        }
        throw new RuntimeException("Unknown parameter mode " + paramMode);
    }

    public int set(int paramMode, int position, int value, List<Integer> program) {
        if (paramMode == POSITION) {
            return program.set(program.get(position), value);
        } else if (paramMode == IMMEDIATE) {
            throw new RuntimeException("Cannot set values in Immediate mode");
        }
        throw new RuntimeException("Unknown parameter mode " + paramMode);
    }
}
