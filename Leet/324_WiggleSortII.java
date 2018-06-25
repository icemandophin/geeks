/*
1. find mid element of array: AVG O(N) time O(1) space
2. create mapping index: (1 + 2 * i) % (n | 1)
example:
Original idx: 0    1    2    3    4    5
Mapped idx:   1    3    5    0    2    4
3. compare each mapped value with mid value:
elements smaller than mid are put into the last even slots
elements larger than mid are put into the first odd slots
*/
public class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int median = findKthLargest(nums, (n + 1) / 2, 0, n - 1);
        // denotes the position where the next larger-than median element will be inserted
        int low = 0;
        // denotes the position where the next smaller-than median element will be inserted
        int high = n - 1;
        int i = 0;

        while (i <= high) {
            if (nums[idx(i, n)] > median) {
                swap(nums, idx(low++, n), idx(i++, n));
            } else if (nums[idx(i, n)] < median) {
                swap(nums, idx(high--, n), idx(i, n));
            } else {
                i++;
            }
        }
    }
    // use 'findKthLargest' function from LC 215
    // to get the median element in average O(n) time and O(1) space
    private int findKthLargest(int[] nums, int k, int low, int high) {
        int pivot = nums[low];
        int idx = low + 1;

        for (int i = idx; i <= high; i++) {
            if (nums[i] > pivot) {
                swap(nums, i, idx++);
            }
        }

        nums[low] = nums[--idx];
        nums[idx] = pivot;
        int count = idx - low + 1;

        if (count == k) {
            return nums[idx];
        } else if (count < k) {
            return findKthLargest(nums, k - count, idx + 1, high);
        } else {
            return findKthLargest(nums, k, low, idx - 1);
        }
    }

    private int idx(int i, int n) {
        // (n | 1) calculates the nearest odd that is not less than n
        return (1 + 2 * i) % (n | 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
