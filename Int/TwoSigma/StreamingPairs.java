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
                // stream is blocking queue
                // no need lock
                // each time new element coming always print all matching pairs 
                double last = stream.take();
                // printPairs read/write to both queues
                // need to lock before R/W                
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
        // always add cur element to its "own" queue
        // then compare to the othe queue and print
        // thread_1's q1 is thread_2's q2
        queue1.offer(last);
        // remove the other queue's "old" element
        while (!queue2.isEmpty() && queue2.peek() <= last - 1) {
            queue2.poll();
        }
        // for each element in the other queue 
        // print every pair within distance 1: (a, b1), (a, b2) ...
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
