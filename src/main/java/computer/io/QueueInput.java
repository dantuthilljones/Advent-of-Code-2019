package computer.io;

import java.util.Queue;

public class QueueInput implements ComputerInput {

    private Queue<Integer> queue;

    public QueueInput(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public int get() {
        return queue.remove();
    }

    public void setQueue(Queue<Integer> queue) {
        this.queue = queue;
    }
}
