package Day02;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    private static final int ADD = 1, MULTIPLY = 2, STOP = 99;

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

    @Test
    public void testPart1() throws IOException {
        String input = Files.readString(Paths.get("input/Day02/intcodes.txt"));
        List<Integer> program = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        //set index 1 to 12 and 2 to 2
        program.set(1, 12);
        program.set(2, 2);

        executeProgram(program);
        System.out.println("The solution to day 2 part 1 is:");
        System.out.println(program.get(0));
    }

    @Test
    public void testPart2() throws IOException {
        String input = Files.readString(Paths.get("input/Day02/intcodes.txt"));
        List<Integer> initialState = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        for(int noun = 0; noun < 100; noun++) {
            for(int verb = 0; verb < 100; verb++) {
                List<Integer> program = copy(initialState);

                //set inputs
                program.set(1, noun);
                program.set(2, verb);
                executeProgram(program);

                int result = program.get(0);
                if (result == 19690720) {
                    System.out.println("The solution to day 2 part 2 is:");
                    System.out.println(100 * noun + verb);
                }
            }
        }
    }

    @Test
    public void testExecuteProgram() {
        List<Integer> program = Lists.newArrayList(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50);
        executeProgram(program);

        List<Integer> expectedResult = Lists.newArrayList(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50);

        Assert.assertEquals(expectedResult, program);
    }

    private static List<Integer> copy(List<Integer> list) {
        return list.stream().collect(Collectors.toList());
    }
}
