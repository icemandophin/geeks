/*
double heap approach:
store incoming elements in minHeap or maxHeap (size diff <= 1)
total size even: return (maxHeap.peek + minHeap.peek)/2
total size odd: return maxHeap.peek (always keep maxHeap size bigger)
*/
class MedianFinder {
    PriorityQueue<Integer> maxH;
    PriorityQueue<Integer> minH;
    /** initialize your data structure here. */
    public MedianFinder() {
        // implement max heap with reverse order of PQ
        maxH = new PriorityQueue<Integer>(Collections.reverseOrder());
        minH = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        // always add to maxHeap first
        // then ensure maxHeap root is smaller than minHeap root
        maxH.offer(num);
        minH.offer(maxH.poll());
        // keep maxHeap size no smaller than minHeap size
        if (maxH.size() < minH.size()) {
            maxH.offer(minH.poll());
        }
    }

    public double findMedian() {
        if (maxH.size() == minH.size()) {
            // total size even
            // notice int=>double conversion should happen BEFORE /2
            return (double)((maxH.peek() + minH.peek())) / 2;
        }
        else {
            // total size odd
            // median is in maxHeap
            return (double)maxH.peek();
        }
    }
}
