class IDAllocate {

    Set<Integer> used;
    Queue<Integer> availQueue;
    int max;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public IDAllocate(int maxNumbers) {

        used = new LinkedHashSet<>();
        availQueue = new LinkedList<>();
        for(int i = 0; i<maxNumbers;i++)
        {
            availQueue.offer(i);
        }
        max = maxNumbers;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int value;
        if(availQueue.size()>0)
        {
            value = availQueue.poll();
            used.add(value);
            return value;
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(used.contains(number))
            return false;
        if(number>=max||number<0)
            return false;
        return true;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(used.remove(number))
            availQueue.offer(number);
    }
}

//use bitset
class PhoneDirectory {
  BitSet bitSet;
    int maxNum;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        maxNum = maxNumbers;
        bitSet = new BitSet(maxNum);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int val = bitSet.nextClearBit(0);
        if(val>=maxNum)
            return -1;
         bitSet.flip(val);
         return val;

    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !bitSet.get(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        bitSet.clear(number);
    }
}
