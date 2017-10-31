public class MinHeap {
    // for root list[i]:
    // left child: list[2*i+1], right child: list[2*i+2]
    private ArrayList<Integer> list;

    public MinHeap() {
        this.list = new ArrayList<Integer>();
    }

    public MinHeap(ArrayList<Integer> input) {
        this.list = input;
        buildHeap();
    }

    public void buildHeap() {
        // only need to heapify [0, N/2]
        // since the leaf nodes [N/2+1, N-1] need no heapify
        for (int i = list.size() / 2; i>= 0; --i) {
            minHeapify(i);
        }
    }
    // heapify subtree with list[i] as root
    public void minHeapify(int i) {
        // find left and right child
        int ln = 2 * i + 1;
        int rn = 2 * i + 2;
        // current min node
        int small = i;
        int n = list.size();
        // swap left/right tree with root if it is smaller than root
        // then heapify the swapped node/subtree
        if (ln <= n - 1 && list.get(ln) < list.get(i)) {
            small = ln;
        }
        if (rn <= n - 1 && list.get(rn) < list.get(i)) {
            small = rn;
        }
        if (small != i) {
            int tmp = list.get(i);
            list.set(i, list.get(small));
            list.set(small, tmp);
            minHeapify(small);
        }
    }

    public int extractMin() {
        if (list.size() == 0) {
            throw new IllegalStateException("MinHeap is EMPTY");
        }
        else if (list.size() == 1) {
            int min = list.remove(0);
            return min;
        }
        // poll the root
        // move last node to root
        // and heapify tree again
        int min = list.get(0);
        int lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);
        // bubble-down until heap property is maintained
        minHeapify(0);
        // return min key
        return min;
    }
}
