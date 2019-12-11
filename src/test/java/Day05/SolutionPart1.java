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

public class SolutionPart1 {

    @Test
    public void testPart1() throws IOException {
        String input = Files.readString(Paths.get("input/Day05/program.txt"));
        List<Integer> program = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());


        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager),
                        new Output(parameterManager)
                ));

        Queue<Integer> inputs = new ArrayDeque<>() {{
            add(1);
        }};

        List<Integer> output = computer.runProgram(program, inputs);

        System.out.println("The solution to day 5 part 1 is:");
        System.out.println(output.get(output.size() -1));
    }

    @Test
    public void testAdd() {
        ParameterManager parameterManager = new ParameterManager();
        Operation add = new Add(parameterManager);

        Assert.assertEquals(1, add.opCode());

        //perform the add
        List<Integer> program = Lists.newArrayList(1, 0, 1, 4, 0);
        Assert.assertEquals(4, add.perform(
                program,
                0,
                new ArrayDeque<>(),
                Lists.newArrayList()));

        Assert.assertArrayEquals(new Object[] {1, 0, 1, 4, 1}, program.toArray());

        program = Lists.newArrayList(101, 456, 1, 4, 0);
        Assert.assertEquals(4, add.perform(
                program,
                0,
                new ArrayDeque<>(),
                Lists.newArrayList()));

        Assert.assertArrayEquals(new Object[] {101, 456, 1, 4, 912}, program.toArray());

        program = Lists.newArrayList(1001, 0, 111, 4, 0);
        Assert.assertEquals(4, add.perform(
                program,
                0,
                new ArrayDeque<>(),
                Lists.newArrayList()));

        Assert.assertArrayEquals(new Object[] {1001, 0, 111, 4, 1112}, program.toArray());

        program = Lists.newArrayList(1101, 200, 123, 4, 0);
        Assert.assertEquals(4, add.perform(
                program,
                0,
                new ArrayDeque<>(),
                Lists.newArrayList()));

        Assert.assertArrayEquals(new Object[]{1101, 200, 123, 4, 323}, program.toArray());

        program = Lists.newArrayList(1101, 100, -1, 4, 0);
        Assert.assertEquals(4, add.perform(
                program,
                0,
                new ArrayDeque<>(),
                Lists.newArrayList()));

        Assert.assertArrayEquals(new Object[]{1101, 100, -1, 4, 99}, program.toArray());

    }

    @Test
    public void testMultiply() {
        ParameterManager parameterManager = new ParameterManager();
        Operation add = new Multiply(parameterManager);

        Assert.assertEquals(2, add.opCode());

        List<Integer> program = Lists.newArrayList(1002,4,3,4,33);
        Assert.assertEquals(4, add.perform(
                program,
                0,
                new ArrayDeque<>(),
                Lists.newArrayList()));

        Assert.assertArrayEquals(new Object[] {1002,4,3,4,99}, program.toArray());
    }


}
