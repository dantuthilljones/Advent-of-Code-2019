package Day07;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import computer.*;
import computer.io.ListOutput;
import computer.io.QueueInput;
import computer.operations.*;
import org.junit.Test;
import util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SolutionPart1 {
    @Test
    public void testPart1() throws IOException {
        String intcodes = Files.readString(Paths.get("input/Day07/intcodes.txt"));
        List<Integer> program = Arrays.stream(intcodes.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        ListOutput output = new ListOutput();
        QueueInput input = new QueueInput(Utils.queue());
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
                        new Equals(parameterManager)
                ));

        Set<Integer> possiblePhases = ImmutableSet.of(0, 1, 2, 3, 4);
        Collection<List<Integer>> permutations = Collections2.permutations(possiblePhases);

        int max = permutations.stream()
                .mapToInt(inputPhases -> runSimulation(inputPhases, computer, program, input, output))
                .max()
                .getAsInt();

        System.out.println("The solution to day 7 part 1 is:");
        System.out.println(max);
    }

    private static int runSimulation(List<Integer> inputPhases,
                                     IntcodeComputer computer,
                                     List<Integer> program,
                                     QueueInput input,
                                     ListOutput output) {
        int inputSignal = 0;
        for (int amp = 0; amp < inputPhases.size(); amp++) {
            Queue<Integer> inputs = Utils.queue(inputPhases.get(amp), inputSignal);
            input.setQueue(inputs);
            output.reset();
            computer.runProgram(program);

            inputSignal = output.getOutput().get(0);
        }
        return inputSignal;
    }
}
