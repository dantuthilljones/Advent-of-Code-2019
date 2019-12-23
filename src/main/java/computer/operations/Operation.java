package computer.operations;

import java.util.List;

public interface Operation {

    int perform(List<Long> program, int position);

    int opCode();

}
