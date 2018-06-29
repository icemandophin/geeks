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

/*
实现一个分配id的类，id可以是0~MAX_ID-1中的一个值，分配出去的id必须唯一，类里面还有个回收id的函数，
总而言之就是实现这个类的两个函数allocate和dellocate，数组结构自己设计。我说了两个方案，
一个用queue，一个用bool数组。他让我先讨论用queue的情况，写了代码，用queue很直接，然后他让改进时间和空间，
方案就是用一个自增的整数，表示还没被分配的id，初始化为0，再有一个队列，每当dellocate时，
就将这个回收的id放到一个队列里。每当allocate时，先判断这个队列里有没有id，有的话拿出来返回，
没有就拿那个整数，并另其+=1，这样的话构造函数的复杂度就变成O(1)了，同时空间也省了很多。
接着让讨论bool数组的情况，这个方法比较糟糕，初始化O(n),allocateO(n),唯一好处就是空间比queue省一点。
然后他让改进，想了一会无果，他提示有没有办法可以让allocate更快呢，快32倍，快64倍。然后我就说用bit，
每次我可以扫32位（int），然后如果这个int大于0的话，就说明这里面有一个id可用，然后就可以用常数级的方法找出这个id。
他说还有没有更快的，比如存储一些临时信息？我就基于前面的方法和这个hint想到了用线段树，
这样的话，初始化O(n),allocateO(logn),dellocateO(logn)，然后他让我写了代码，假设线段树已经建好了，
要我实现allocate函数，pls note that 在实现的过程中，我假设一个数节点存着区间开始和结束点，
可用id数量，左孩子和右孩子指针。写完后他让我优化空间，我说不需要开始和结束点，因为可以通过计算算出来。
让我再优化，我说可用id数量可以换成一个bool值。让我再优化，让我省去两个指针，我说或许我可以用堆的数组实现方式来做，
但是这不一定是一颗满二叉树，他说那假设maxid是2^n吧，我说那可以了，我只用一个数组就好了，
a的左右孩子分别为a[2i+1],a[2i+2]。然后最终方案就变成了只需要2*MAX_ID-1个bit的空间来实现。
总结一下就是用queue耗空间省时间，数组耗时间省空间。
*/
