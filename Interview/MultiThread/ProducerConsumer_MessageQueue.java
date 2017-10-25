import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
    // impplement Msg Queue using shared blocking queue
    // volatile - change visible across threads
    private volatile BlockingQueue<Integer> msgQueue = new LinkedBlockingQueue<Integer>();

    public Producer(final int size)  {
        //producer thread
        Thread p = new Thread(new Runnable() {
            @Override
            public void run() {
                // produce and enqueue n items
                for (int i = 0; i < size; i++) {
                        try {
                            System.out.println("Producing: " + i);
                            msgQueue.put(i);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                }
            }

        });
        p.start();
    }

    public Consumer(final int size) {
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                // consume items in the queue
                for (int i = 0; i < size; i++) {
                    try {
                        System.out.println("Consuming: " + msgQueue.take());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        });
        c.start();
    }

    public static void main(String [] args) {
        Producer p = new Producer(10);
        Consumer c1 = new Consumer(3);
        Consumer c2 = new Consumer(2);
    }

}
