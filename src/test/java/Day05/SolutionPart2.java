package Day05;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class SolutionPart2 {

    @Test
    public void testPart2() throws IOException {
        String input = Files.readString(Paths.get("input/Day05/program.txt"));
        List<Integer> program = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());


        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager),
                        new Output(parameterManager),
                        new JumpIfFalse(parameterManager),
                        new JumpIfTrue(parameterManager),
                        new LessThan(parameterManager),
                        new Equals(parameterManager)
                ));

        Queue<Integer> inputs = new ArrayDeque<>() {{
            add(5);
        }};

        List<Integer> output = computer.runProgram(program, inputs);

        System.out.println("The solution to day 5 part 2 is:");
        System.out.println(output.get(0));
    }

    @Test
    public void testProgramWithJumpsPositionMode() throws IOException {
        List<Integer> program = Lists.newArrayList(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9);


        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager),
                        new Output(parameterManager),
                        new JumpIfFalse(parameterManager),
                        new JumpIfTrue(parameterManager),
                        new LessThan(parameterManager),
                        new Equals(parameterManager)
                ));

        Queue<Integer> inputs = new ArrayDeque<>() {{
            add(0);
        }};
        List<Integer> output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{0}, output.toArray());


        inputs = new ArrayDeque<>() {{
            add(123);
        }};
        output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{1}, output.toArray());
    }

    @Test
    public void testProgramWithJumpsImmediateMode() throws IOException {


        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager),
                        new Output(parameterManager),
                        new JumpIfFalse(parameterManager),
                        new JumpIfTrue(parameterManager),
                        new LessThan(parameterManager),
                        new Equals(parameterManager)
                ));


        List<Integer> program = Lists.newArrayList(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1);
        Queue<Integer> inputs = new ArrayDeque<>() {{
            add(123);
        }};
        List<Integer> output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{1}, output.toArray());


        program = Lists.newArrayList(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1);
        inputs = new ArrayDeque<>() {{
            add(0);
        }};
        output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{0}, output.toArray());
    }

    @Test
    public void testProgramWithEqualsPositionMode() throws IOException {
        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager),
                        new Output(parameterManager),
                        new JumpIfFalse(parameterManager),
                        new JumpIfTrue(parameterManager),
                        new LessThan(parameterManager),
                        new Equals(parameterManager)
                ));


        List<Integer> program = Lists.newArrayList(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8);
        Queue<Integer> inputs = new ArrayDeque<>() {{
            add(8);
        }};
        List<Integer> output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{1}, output.toArray());


        program = Lists.newArrayList(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8);
        inputs = new ArrayDeque<>() {{
            add(123);
        }};
        output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{0}, output.toArray());
    }

    @Test
    public void testProgramWithEqualsImmediateMode() throws IOException {
        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager),
                        new Output(parameterManager),
                        new JumpIfFalse(parameterManager),
                        new JumpIfTrue(parameterManager),
                        new LessThan(parameterManager),
                        new Equals(parameterManager)
                ));


        List<Integer> program = Lists.newArrayList(3, 3, 1108, -1, 8, 3, 4, 3, 99);
        Queue<Integer> inputs = new ArrayDeque<>() {{
            add(8);
        }};
        List<Integer> output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{1}, output.toArray());


        program = Lists.newArrayList(3, 3, 1108, -1, 8, 3, 4, 3, 99);
        inputs = new ArrayDeque<>() {{
            add(123);
        }};
        output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{0}, output.toArray());
    }

    @Test
    public void testProgramWithLessThanPositionMode() throws IOException {
        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager),
                        new Output(parameterManager),
                        new JumpIfFalse(parameterManager),
                        new JumpIfTrue(parameterManager),
                        new LessThan(parameterManager),
                        new Equals(parameterManager)
                ));


        List<Integer> program = Lists.newArrayList(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8);
        Queue<Integer> inputs = new ArrayDeque<>() {{
            add(4);
        }};
        List<Integer> output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{1}, output.toArray());


        program = Lists.newArrayList(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8);
        inputs = new ArrayDeque<>() {{
            add(8);
        }};
        output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{0}, output.toArray());
    }

    @Test
    public void testProgramWithLessThanImmediateMode() throws IOException {
        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager),
                        new Output(parameterManager),
                        new JumpIfFalse(parameterManager),
                        new JumpIfTrue(parameterManager),
                        new LessThan(parameterManager),
                        new Equals(parameterManager)
                ));


        List<Integer> program = Lists.newArrayList(3, 3, 1107, -1, 8, 3, 4, 3, 99);
        Queue<Integer> inputs = new ArrayDeque<>() {{
            add(4);
        }};
        List<Integer> output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{1}, output.toArray());


        program = Lists.newArrayList(3, 3, 1107, -1, 8, 3, 4, 3, 99);
        inputs = new ArrayDeque<>() {{
            add(8);
        }};
        output = computer.runProgram(program, inputs);
        Assert.assertArrayEquals(new Object[]{0}, output.toArray());
    }
}
