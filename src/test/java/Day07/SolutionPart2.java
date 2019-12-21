package Day07;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import computer.*;
import computer.io.*;
import computer.operations.*;
import org.junit.Test;
import util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

public class SolutionPart2 {
    @Test
    public void testPart1() throws IOException {
        String intcodes = Files.readString(Paths.get("input/Day07/intcodes.txt"));
        List<Integer> program = Arrays.stream(intcodes.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        Set<Integer> possiblePhases = ImmutableSet.of(5, 6, 7, 8, 9);
        Collection<List<Integer>> permutations = Collections2.permutations(possiblePhases);

        int max = permutations.stream()
                .mapToInt(inputPhases -> runSimulation(inputPhases, program))
                .max()
                .getAsInt();

        System.out.println("The solution to day 7 part 2 is:");
        System.out.println(max);
    }

    private static int runSimulation(List<Integer> inputPhases,
                                     List<Integer> program) {

        BlockingQueue<Integer> blockingQueueA = new LinkedBlockingDeque();
        BlockingQueue<Integer> blockingQueueB = new LinkedBlockingDeque();
        BlockingQueue<Integer> blockingQueueC = new LinkedBlockingDeque();
        BlockingQueue<Integer> blockingQueueD = new LinkedBlockingDeque();
        BlockingQueue<Integer> blockingQueueE = new LinkedBlockingDeque();

        ComputerInput inputA = new BlockingQueueInput(blockingQueueA);
        ComputerOutput outputA = new QueueOfferOutput(blockingQueueB);

        ComputerInput inputB = new BlockingQueueInput(blockingQueueB);
        ComputerOutput outputB = new QueueOfferOutput(blockingQueueC);

        ComputerInput inputC = new BlockingQueueInput(blockingQueueC);
        ComputerOutput outputC = new QueueOfferOutput(blockingQueueD);

        ComputerInput inputD = new BlockingQueueInput(blockingQueueD);
        ComputerOutput outputD = new QueueOfferOutput(blockingQueueE);

        ComputerInput inputE = new BlockingQueueInput(blockingQueueE);
        QueueOfferOutput outputE = new QueueOfferOutput(blockingQueueA);

        blockingQueueA.add(inputPhases.get(0));
        blockingQueueA.add(0);
        blockingQueueB.add(inputPhases.get(1));
        blockingQueueC.add(inputPhases.get(2));
        blockingQueueD.add(inputPhases.get(3));
        blockingQueueE.add(inputPhases.get(4));

        Thread threadA = new Thread(() -> {
            IntcodeComputer computer = buildComputer(inputA, outputA);
            computer.runProgram(program);
        });
        threadA.setName("Computer A");
        threadA.start();

        Thread threadB = new Thread(() -> {
            IntcodeComputer computer = buildComputer(inputB, outputB);
            computer.runProgram(program);
        });
        threadB.setName("Computer B");
        threadB.start();

        Thread threadC = new Thread(() -> {
            IntcodeComputer computer = buildComputer(inputC, outputC);
            computer.runProgram(program);
        });
        threadC.setName("Computer C");
        threadC.start();

        Thread threadD = new Thread(() -> {
            IntcodeComputer computer = buildComputer(inputD, outputD);
            computer.runProgram(program);
        });
        threadD.setName("Computer D");
        threadD.start();

        IntcodeComputer computer = buildComputer(inputE, outputE);
        computer.runProgram(program);

        return outputE.getQueue().remove();
    }

    private static IntcodeComputer buildComputer(ComputerInput input, ComputerOutput output) {
        ParameterManager parameterManager = new ParameterManager();
        return new IntcodeComputer(
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
    }
}
