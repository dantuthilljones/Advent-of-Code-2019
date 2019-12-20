package computer.io;

import java.util.ArrayList;
import java.util.List;

public class ListOutput implements ComputerOutput {

    private final List<Integer> output;

    public ListOutput() {
        output = new ArrayList<>();
    }

    @Override
    public void output(int value) {
        output.add(value);
    }

    public List<Integer> getOutput() {
        return output;
    }

    public void reset() {
        output.clear();
    }
}
