package computer.io;

import java.util.Queue;

public class QueueInput implements ComputerInput {

    private Queue<Long> queue;

    public QueueInput(Queue<Long> queue) {
        this.queue = queue;
    }

    @Override
    public long get() {
        return queue.remove();
    }

    public void setQueue(Queue<Long> queue) {
        this.queue = queue;
    }
}
