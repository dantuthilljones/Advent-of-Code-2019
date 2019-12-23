package Day09;

import com.google.common.collect.ImmutableList;
import computer.IntcodeComputer;
import computer.ParameterManager;
import computer.io.ListOutput;
import computer.io.QueueInput;
import computer.operations.*;
import org.junit.Test;
import util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionPart1 {
    @Test
    public void testPart1() throws IOException {
        String intcodes = Files.readString(Paths.get("input/Day09/program.txt"));
        List<Long> program = Arrays.stream(intcodes.split(",")).map(Long::parseLong).collect(Collectors.toList());

        ListOutput output = new ListOutput();
        QueueInput input = new QueueInput(Utils.queue(1));
        ParameterManager parameterManager = new ParameterManager();
        IntcodeComputer computer = new IntcodeComputer(
                ImmutableList.of(
                        new Add(parameterManager),
                        new Multiply(parameterManager),
                        new Input(parameterManager, input),
                        new Output(parameterManager, output),
                        new JumpIfFalse(parameterManager),
                        new JumpIfTrue(parameterManager),
                        new LessThan(parameterManager),
                        new Equals(parameterManager),
                        new AdjustRelativeBase(parameterManager)
                ));

        computer.runProgram(program);

        System.out.println("The solution to day 8 part 1 is:");
        System.out.println(output.getOutput().get(0));
    }
}
