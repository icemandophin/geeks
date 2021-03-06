// LC 379
/*
Design a Phone Directory which supports the following operations:
get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);
// It can return any available phone number. Here we assume it returns 0.
directory.get();
// Assume it returns 1.
directory.get();
// The number 2 is available, so return true.
directory.check(2);
// It returns 2, the only number that is left.
directory.get();
// The number 2 is no longer available, so return false.
directory.check(2);
// Release number 2 back to the pool.
directory.release(2);
// Number 2 is available again, return true.
directory.check(2);
*/
/*
optimize: replace hash set with BitSet
allows for super large input size
*/
public class PhoneDirectory {
    // BitSet is made by LONG + LONG + ...
    // each long can hold 64 phone numbers
    private BitSet pool;
    private int maxNum;
    private int next;

    public PhoneDirectory(int vol) {
        maxNum = vol;
        pool = new BitSet(maxNum);
        next = 0;
    }

    public int get() {
        if (next == maxNum) {
            // run out of all numbers
            return -1;
        }
        int res = next;
        pool.set(next);
        next = pool.nextClearBit(next);
        return res;
    }

    public boolean check(int num) {
        return !pool.get(num);
    }

    public void release(int num) {
        pool.clear(num);
        if (num < next) {
            next = num;
        }
    }
}

/*
HashSet + Queue:
*/
public class PhoneDirectory {
    // record used numbers
    private Set<Integer> used;
    // record available numbers
    private Queue<Integer> available;
    private int maxNumbers;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        used = new HashSet<Integer>();
        available = new LinkedList<Integer>();

        for (int i = 0; i < maxNumbers; i++) {
            available.offer(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (available.isEmpty()) {
            return -1;
        }

        Integer peek = available.poll();
        used.add(peek);
        return peek;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (used.remove(number)) {
            available.offer(number);
        }
    }
}