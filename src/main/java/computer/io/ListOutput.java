package computer.io;

import java.util.ArrayList;
import java.util.List;

public class ListOutput implements ComputerOutput {

    private final List<Long> output;

    public ListOutput() {
        output = new ArrayList<>();
    }

    @Override
    public void output(long value) {
        output.add(value);
    }

    public List<Long> getOutput() {
        return output;
    }

    public void reset() {
        output.clear();
    }
}
