public class BlockingQueue {
    List<Integer> queue = new LinkedList<Integer>();
    int maxSize = 0;
    public BlockingQueue(int max) {
        maxSize = max;
    }

    public synchronized void  enqueue(int i) {
        // check if queue is full before adding
        while(queue.size() == maxSize) {
            wait();
        }
        if (queue.size() == 0) {
            // if adding to an empty queue, notify other waiting
            // other waiting threads can start in parallel
            notifyAll();
        }
        queue.add(i);
    }

    public synchronized int dequeue() {
        while(queue.size() == 0) {
            // if trying to remove to an empty queue.
            wait();
        }
        if (queue.size() == maxSize) {
            // if removing from a full queue queue, notify other waiting threads
            notifyAll();
        }
        return queue.remove(0);
    }
}
