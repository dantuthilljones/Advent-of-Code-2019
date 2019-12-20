package computer.io;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueInput implements ComputerInput {
    private BlockingQueue<Integer> queue;

    public BlockingQueueInput(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public int get() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
}
