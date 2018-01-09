/*
Binary search approach: O(nlog(max_val−min_val))
reference: https://leetcode.com/articles/maximum-average-subarray-ii/
*/
public class Solution {
    public double maxAverage(int[] nums, int k) {
        double max_val = Integer.MIN_VALUE;
        double min_val = Integer.MAX_VALUE;
        for (int i: nums) {
            // traverse the input and find min/max value
            max_val = Math.max(max_val, i);
            min_val = Math.min(min_val, i);
        }
        double prev_mid = max_val, error = Integer.MAX_VALUE;
        while (error > 0.00001) {
            double mid = (max_val + min_val) * 0.5;
            // it is possible to get bigger avg value
            if (check(nums, mid, k))
                min_val = mid;
            else
                max_val = mid;
            error = Math.abs(prev_mid - mid);
            prev_mid = mid;
        }

        return min_val;
    }

    // check whether nums has subarray (>= k) that AVG bigger than mid
    public boolean check(int[] nums, double mid, int k) {
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++) {
            // (a1+a2+a3...+aj)/j ≥ mid
            // => let b[i] = a[i] - mid
            // get sum of b[0] - b[k - 1]
            sum += nums[i] - mid;
        }
        if (sum >= 0) {
            return true;
        }
        for (int i = k; i < nums.length; i++) {
            // add b[i]
            sum += nums[i] - mid;
            // add b[j]
            prev += nums[i - k] - mid;
            // get cur min sum of b[i]
            min_sum = Math.min(prev, min_sum);
            // check if sum[i] - sum[j] >= 0
            if (sum >= min_sum) {
                return true;
            }
        }
        return false;
    }
}
