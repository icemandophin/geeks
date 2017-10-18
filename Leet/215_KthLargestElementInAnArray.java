/*
build heap: O(NlogK)
*/
class Solution {
    public int findKthLargest(int[] a, int k) {
        // implement min heap with pq
        PriorityQueue<Integer> mh = new PriorityQueue<Integer>(k);
        for (Integer i : a) {
            // try to insert ith element
            mh.offer(i);
            // ensure heap size < k
            if (mh.size() > k) {
                mh.poll();
            }
        }
        // heap top is k-th largest
        return mh.peek();
    }
}

/*
quick select/partition: AVG - O(N) MAX - O(N*N)
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
        // recursively partition array and look for kth largest
        return recur(nums, 0, nums.length - 1, nums.length - k + 1);
    }
    public int recur(int[] a, int l, int r, int k) {
        if (l == r) {
            return a[l];
        }
        // partition array into 2 parts and get pivot
        int cur = partition(a, l, r);
        if (cur + 1 == k) {
            // there are n-k elements that bigger than cur pivot
            // hence pivot is the kth larget elements
            return a[cur];
        }
        else if (cur + 1 < k) {
            // get more than k bigger elements
            // kth largest is in the right side of pivot
            // use exising l, r as boundary
            return recur(a, cur + 1, r, k);
        }
        else {
            // search in left side
            // use exising l, r as boundary
            return recur(a, l, cur - 1, k);
        }
    }
    // partition - make left < pivot < right
    // can be reused
    public int partition(int[] a, int l, int r) {
        // init left, right and pivot index
        int left = l;
        int right = r;
        // select random pivot and save its value in pivot
        int pivot = a[left];
        // partition array in place:
        // make left < pivot and right > pivot
        while (left < right) {
            // find 1st smaller element on right side
            while (left < right && a[right] >= pivot) {
                --right;
            }
            // swap and ensure left < pivot < right
            int trans = a[left];
            a[left] = a[right];
            a[right] = trans;
            // find 1st bigger element on left side
            while (left < right && a[left] <= pivot) {
                ++left;
            }
            // swap and ensure left < pivot < right
            trans = a[right];
            a[right] = a[left];
            a[left] = trans;
        }
        // put pivot value back to array
        a[left] = pivot;
        return left;
    }
}
