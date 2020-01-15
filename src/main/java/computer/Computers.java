package computer;

import com.google.common.collect.ImmutableList;
import computer.io.ComputerInput;
import computer.io.ComputerOutput;
import computer.io.ListOutput;
import computer.io.QueueInput;
import computer.operations.*;
import util.Utils;

import java.util.List;
import java.util.Queue;

public class Computers {

    public static List<Long> runProgram(List<Long> program) {
        return runProgramWithInputs(program, Utils.queue());
    }

    public static List<Long> runProgramWithInputs(List<Long> program, Queue<Long> inputs) {
        ListOutput output = new ListOutput();
        QueueInput input = new QueueInput(inputs);
        IntcodeComputer computer = Computers.makeWithIO(input, output);

        computer.runProgram(program);
        return output.getOutput();
    }


    public static IntcodeComputer makeWithIO(ComputerInput input, ComputerOutput output) {
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
                        new Equals(parameterManager),
                        new AdjustRelativeBase(parameterManager)
                ));
    }
}
