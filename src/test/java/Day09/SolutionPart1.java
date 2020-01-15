package Day09;

import com.google.common.collect.ImmutableList;
import computer.Computers;
import computer.IntcodeComputer;
import computer.ParameterManager;
import computer.io.ListOutput;
import computer.io.QueueInput;
import computer.operations.*;
import org.junit.Assert;
import org.junit.Test;
import util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SolutionPart1 {
    @Test
    public void testPart1() throws IOException {
        String intcodes = Files.readString(Paths.get("input/Day09/program.txt"));
        List<Long> program = Arrays.stream(intcodes.split(",")).map(Long::parseLong).collect(Collectors.toList());

        List<Long> outputs = Computers.runProgramWithInputs(program, Utils.longQueue(1));

        System.out.println("The solution to day 9 part 1 is:");
        System.out.println(outputs.get(0));
    }

    @Test
    public void testPart2() throws IOException {
        String intcodes = Files.readString(Paths.get("input/Day09/program.txt"));
        List<Long> program = Arrays.stream(intcodes.split(",")).map(Long::parseLong).collect(Collectors.toList());

        List<Long> outputs = Computers.runProgramWithInputs(program, Utils.longQueue(2));

        System.out.println("The solution to day 9 part 2 is:");
        System.out.println(outputs.get(0));
    }

    @Test
    public void testExample1() {
        List<Long> program = ImmutableList.of(109l,1l,204l,-1l,1001l,100l,1l,100l,1008l,100l,16l,101l,1006l,101l,0l,99l);
        List<Long> output = Computers.runProgram(program);
        Assert.assertEquals(program, output);
    }

    @Test
    public void testExample2() {
        List<Long> program = ImmutableList.of(1102l,34915192l,34915192l,7l,4l,7l,99l,0l);
        List<Long> output = Computers.runProgram(program);
        Assert.assertEquals(16, Objects.toString(output).length());
    }

    @Test
    public void testExample3() {
        List<Long> program = ImmutableList.of(104l,1125899906842624l,99l);
        List<Long> output = Computers.runProgram(program);
        Assert.assertEquals(1125899906842624l, (long) output.get(0));
    }
}
