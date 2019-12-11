package Day05;

import java.util.List;
import java.util.Queue;

public interface Operation {

    int perform(List<Integer> program, int position, Queue<Integer> inputs, List<Integer> outputs);

    int opCode();

}
