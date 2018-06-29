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
get(), release() and check() should be O(1) => queue + hashset
*/
public class PhoneDirectory {
    private Set<Integer> used;
    private Queue<Integer> pool;
    private int maxNum;
    // create all numbers during construction
    public PhoneDirectory(int vol) {
        maxNum = vol;
        used = new HashSet<>();
        pool = new LinkedList<>();
        for (int i = 0; i < maxNum; ++i) {
            // enqueue all numbers
            pool.offer(i);
        }
    }

    public int get() {
        if (pool.isEmpty()) {
            return -1;
        }
        Integer res = pool.poll();
        used.add(res);
        return res;
    }

    public boolean check(int num) {
        return !used.contains(num);
    }

    public void release(int num) {
        if (used.remove(num)) {
            pool.offer(num);
        }
    }
}

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
        return !pool.check(num);
    }

    public void release(int num) {
        pool.clear(num);
        if (num < next) {
            next = num;
        }
    }
}
