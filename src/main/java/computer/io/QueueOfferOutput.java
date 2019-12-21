package computer.io;

import java.util.Queue;

public class QueueOfferOutput implements ComputerOutput {

    private final Queue<Integer> queue;

    public QueueOfferOutput(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void output(int value) {
        System.out.println(Thread.currentThread().getName() + ": Offering " + value);
        queue.offer(value);
    }

    public Queue<Integer> getQueue() {
        return queue;
    }
}
