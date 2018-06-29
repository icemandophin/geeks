/*
basic approach:
get(), release() and check() should be O(1)
=> queue for available numbers
hashset to record used numbers
*/
class PhoneDirectory {
    private Set<Integer> used;
    private Queue<Integer> pool;
    private int size;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        size = maxNumbers;
        used = new HashSet<>();
        pool = new LinkedList<>();

        for (int i = 0; i < size; ++i) {
            // enqueue all numbers
            pool.offer(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (pool.isEmpty()) {
            return -1;
        }

        Integer res = pool.poll();
        used.add(res);

        return res;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (used.remove(number)) {
            pool.offer(number);
        }
    }
}

/*
optimize: replace hash set with BitSet
allows for super large input size

其原理是：
1，先找出数据中最大值maxvalue
2，声明一个BitSet bs,它的size是maxvalue+1
3，遍历数据source，bs[source[i]]设置成true.
*/
public class PhoneDirectory {
    // BitSet is made by LONG + LONG + ...
    // each long can hold 64 phone numbers
    // allocate: O(N)
    private BitSet pool;
    private int maxNum;
    private int next;

    public PhoneDirectory(int vol) {
        maxNum = vol;
        pool = new BitSet(maxNum);
        next = 0;
    }
    // this can take O(n)
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

/*
Segment tree implementation:
store in array like heap: for parent a[i]
its left child: a[2*i + 1] right child: a[2*i + 2]
Space complexity: O(2n)
Time complexity: get O(2logn); check: O(1); release: O(logn).
*/
class PhoneDirectory {
    // a[i] == true indicates there is number
    // available in the subtree with a[i] as root
    private boolean[] segTree;
    private int size;

    public PhoneDirectory(int num) {
        size = num;
        // add one extra slot to avoid index out of bound
        // when getting last element
        // e.g: when size = 3 and get(2)
        // segTree[2 * 2 + 2] need to be accessible
        segTree = new boolean[2 * size];
        for (int i = 0; i < segTree.length; ++i) {
            segTree[i] = true;
        }
    }
    // O(lgN)
    public int get() {
        // root is false => run out of numbers
        if (segTree[0] == false) {
            return -1;
        }

        int i = 0;
        int n = segTree.length;
        while (i < n / 2) {
            // check left/right node
            // move to child if valid node exist in subtree
            if ((2 * i + 1 < n) && (segTree[2 * i + 1])) {
                i = 2 * i + 1;
            } else if ((2 * i + 2 < n) && (segTree[2 * i + 2])) {
                i = 2 * i + 2;
            }
        }
        // get actual number from leaf node
        int res = i - size;
        // update segment tree
        // if either child has free number set to true
        // else set to false
        segTree[i] = false;
        i /= 2;
        do {
            boolean avail = false;
            if (2 * i + 1 < n) {
                avail |= segTree[2 * i + 1];
            }
            if (2 * i + 2 < n) {
                avail |= segTree[2 * i + 2];
            }
            segTree[i] = avail;
            i /= 2;
        } while (i > 0);

        return res;
    }
    // set to true
    public boolean check(int num) {
        if (num >= 0 && num < size && segTree[num + size]) {
            return true;
        } else {
            return false;
        }
    }
    // O(lgN)
    public void release(int num) {
        // locate the leaf node of cur num
        int i = num + size;
        // set all its ancestors to valid
        while (i > 0) {
           segTree[i] = true;
           i /= 2;
        }
    }
}
