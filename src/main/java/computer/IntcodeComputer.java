package computer;

import computer.operations.Operation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IntcodeComputer {

    private final Map<Integer, Operation> operations;
    private final int HALT = 99;

    public IntcodeComputer(List<Operation> operations) {
        this.operations = operations.stream().collect(Collectors.toMap(Operation::opCode, Function.identity()));
    }

    public void runProgram(List<Long> input) {
        IntcodeProgram program = new IntcodeProgram(input);//copy program

        int position = 0;
        while(true) {
            int opcode = (int) (program.get(position) % 100);
            if (opcode == HALT) {
                return;
            }
            Operation operation =  operations.get(opcode);
            position = operation.perform(program, position);
        }
    }

}
