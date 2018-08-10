class MyCircularQueue {
    private int size;
    private ArrayList<Integer> myCircularQueue;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.size = k;
        this.myCircularQueue = new ArrayList<Integer>();
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(myCircularQueue.size() < size){
            myCircularQueue.add(value);
            return true;
        }
        else return false;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(myCircularQueue.size()>=1){
            myCircularQueue.remove(0);
            return true;
        }
        else return false;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(myCircularQueue.size()>=1){
            return myCircularQueue.get(0);
        }
        else return -1;
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(myCircularQueue.size()>=1){
            return myCircularQueue.get(myCircularQueue.size()-1);
        }
        else return -1;
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return myCircularQueue.size() ==0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return myCircularQueue.size() == size;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */