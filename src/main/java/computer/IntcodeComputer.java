package computer;

import computer.io.ComputerInput;
import computer.operations.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IntcodeComputer {

    private final Map<Integer, Operation> operations;
    private final int HALT = 99;

    public IntcodeComputer(List<Operation> operations) {
        this.operations = operations.stream().collect(Collectors.toMap(Operation::opCode, Function.identity()));
    }

    public List<Integer> runProgram(List<Integer> program) {
        program = new ArrayList(program);//copy program
        List<Integer> outputs = new ArrayList<>();

        int position = 0;
        while(true) {
            int opcode = program.get(position) % 100;
            if (opcode == HALT) {
                return outputs;
            }
            Operation operation =  operations.get(opcode);
            position = operation.perform(program, position);
        }
    }

}
