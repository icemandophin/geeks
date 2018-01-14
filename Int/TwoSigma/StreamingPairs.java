import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class StreamingPair implements Runnable {
    private BlockingQueue<Double> stream;
    private Queue<Double> queue1;
    private Queue<Double> queue2;
    private ReentrantLock lock;

    public StreamingPair(BlockingQueue<Double> stream, Queue<Double> queue1, Queue<Double> queue2, ReentrantLock lock) {
        this.stream = stream;
        this.queue1 = queue1;
        this.queue2 = queue2;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                double last = stream.take();
                lock.lock();

                try {
                    printPairs(last);
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printPairs(double last) {
        queue1.offer(last);

        while (!queue2.isEmpty() && queue2.peek() <= last - 1) {
            queue2.poll();
        }

        for (double num : queue2) {
            if (Math.abs(num - last) < 1) {
                System.out.println(last + ", " + num);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Double> stream1 = new ArrayBlockingQueue<>(3);
        BlockingQueue<Double> stream2 = new ArrayBlockingQueue<>(3);
        stream1.add(1.0);
        stream1.add(1.2);
        stream1.add(3.0);
        stream2.add(1.1);
        stream2.add(2.0);
        stream2.add(3.2);
        Queue<Double> queue1 = new LinkedList<>();
        Queue<Double> queue2 = new LinkedList<>();
        ReentrantLock lock = new ReentrantLock();

        StreamingPair sp1 = new StreamingPair(stream1, queue1, queue2, lock);
        StreamingPair sp2 = new StreamingPair(stream2, queue2, queue1, lock);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(sp1);
        executor.submit(sp2);
    }
}
