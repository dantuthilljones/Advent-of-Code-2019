package computer.io;

import java.util.Queue;

public class QueueOfferOutput implements ComputerOutput {

    private final Queue<Long> queue;

    public QueueOfferOutput(Queue<Long> queue) {
        this.queue = queue;
    }

    @Override
    public void output(long value) {
        System.out.println(Thread.currentThread().getName() + ": Offering " + value);
        queue.offer(value);
    }

    public Queue<Long> getQueue() {
        return queue;
    }
}
