package computer.operations;

import java.util.List;
import java.util.Queue;

public interface Operation {

    int perform(List<Integer> program, int position);

    int opCode();

}
