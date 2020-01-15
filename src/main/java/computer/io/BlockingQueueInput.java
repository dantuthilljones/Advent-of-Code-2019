package computer.io;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueInput implements ComputerInput {
    private BlockingQueue<Integer> queue;

    public BlockingQueueInput(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public long get() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Waiting to take input..");
            int take = queue.take();
            System.out.println(Thread.currentThread().getName() + ": Took input " + take);
            return take;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
}
