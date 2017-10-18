public class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length-k+1];
        TreeMap<Integer, Integer> minHeap = new TreeMap<Integer, Integer>();
        TreeMap<Integer, Integer> maxHeap = new TreeMap<Integer, Integer>(Collections.reverseOrder());

        int minHeapCap = k/2; //smaller heap when k is odd.
        int maxHeapCap = k - minHeapCap;

        for(int i=0; i< k; i++){
            maxHeap.put(nums[i], maxHeap.getOrDefault(nums[i], 0) + 1);
        }
        int[] minHeapSize = new int[]{0};
        int[] maxHeapSize = new int[]{k};
        for(int i=0; i< minHeapCap; i++){
            move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);
        }

        res[0] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
        int resIdx = 1;

        for(int i=0; i< nums.length-k; i++){
            int addee = nums[i+k];
            if(addee <= maxHeap.keySet().iterator().next()){
                add(addee, maxHeap, maxHeapSize);
            } else {
                add(addee, minHeap, minHeapSize);
            }

            int removee = nums[i];
            if(removee <= maxHeap.keySet().iterator().next()){
                remove(removee, maxHeap, maxHeapSize);
            } else {
                remove(removee, minHeap, minHeapSize);
            }

            //rebalance
            if(minHeapSize[0] > minHeapCap){
                move1Over(minHeap, maxHeap, minHeapSize, maxHeapSize);
            } else if(minHeapSize[0] < minHeapCap){
                move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);
            }

            res[resIdx] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
            resIdx++;
        }
        return res;
    }

    public double getMedian(TreeMap<Integer, Integer> bigHeap, TreeMap<Integer, Integer> smallHeap, int[] bigHeapSize, int[] smallHeapSize){
        return bigHeapSize[0] > smallHeapSize[0] ? (double) bigHeap.keySet().iterator().next() : ((double) bigHeap.keySet().iterator().next() + (double) smallHeap.keySet().iterator().next()) / 2.0;
    }

    //move the top element of heap1 to heap2
    public void move1Over(TreeMap<Integer, Integer> heap1, TreeMap<Integer, Integer> heap2, int[] heap1Size, int[] heap2Size){
        int peek = heap1.keySet().iterator().next();
        add(peek, heap2, heap2Size);
        remove(peek, heap1, heap1Size);
    }

    public void add(int val, TreeMap<Integer, Integer> heap, int[] heapSize){
        heap.put(val, heap.getOrDefault(val,0) + 1);
        heapSize[0]++;
    }

    public void remove(int val, TreeMap<Integer, Integer> heap, int[] heapSize){
        if(heap.put(val, heap.get(val) - 1) == 1) heap.remove(val);
        heapSize[0]--;
    }
}
