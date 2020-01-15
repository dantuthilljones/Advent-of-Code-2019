package computer;

import java.util.ArrayList;
import java.util.List;

public class IntcodeProgram extends ArrayList<Long> {

    public IntcodeProgram(List<Long> program) {
        super(program);
    }

    @Override
    public Long get(int position) {
        fillUntil(position);
        return super.get(position);
    }

    @Override
    public Long set(int position, Long value) {
        fillUntil(position);
        return super.set(position, value);
    }

    public void fillUntil(int position) {
        while (position >= size()) {
            add(0l);
        }
    }
}
