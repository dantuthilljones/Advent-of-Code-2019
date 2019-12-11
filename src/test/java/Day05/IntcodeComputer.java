package Day05;

import java.util.List;
import java.util.function.IntConsumer;

public class IntcodeComputer {

    private final List<Integer> input;
    private final List<Integer> program;

    private static final int ADD = 1, MULTIPLY = 2, STOP = 99;

    public IntcodeComputer(List<Integer> input, List<Integer> program) {
        this.input = input;
        this.program = program;
    }

    public List<Integer> compute() {

    }

    private static void executeProgram(List<Integer> program) {
        int index = 0;
        while (doStep(program, index)) {
            index += 4;
        }
    }

    private static boolean doStep(List<Integer> program, int index) {
        int operation = program.get(index);
        if (operation == STOP) {
            return false;
        } else if (operation == ADD) {
            sum(program, program.get(index + 1), program.get(index + 2), program.get(index + 3));
        } else if (operation == MULTIPLY) {
            multiply(program, program.get(index + 1), program.get(index + 2), program.get(index + 3));
        } else {
            throw new RuntimeException("Something went wrong. Recieved opcode " + operation);
        }
        return true;
    }

    private static void sum(List<Integer> program, int index1, int index2, int resultIndex) {
        int num1 = program.get(index1);
        int num2 = program.get(index2);
        int result = num1 + num2;
        program.set(resultIndex, result);
    }

    private static void multiply(List<Integer> program, int index1, int index2, int resultIndex) {
        int num1 = program.get(index1);
        int num2 = program.get(index2);
        int result = num1 * num2;
        program.set(resultIndex, result);
    }

}
