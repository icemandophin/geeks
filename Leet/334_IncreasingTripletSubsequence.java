/*
best approach: O(N) time, O(1) space
hardest to find k since it requires a[k] > max{a[i], a[j]} and a[i] < a[j] exist
during traverse, always remember the smallest 2 elements that visited before
by this way maximize chance to find k that a[k] > prev[2]
*/
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int n = nums.length;
        // init 1st, 2nd smallest visited element
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int cur : nums) {
            // always compare with smallest first
           if (cur <= first) {
               first = cur;
           } else if (cur <= second) {
                second = cur;
           } else {
               return true;
           }
        }

        // not found
        return false;
    }
}

/*
brutal: O(N*N) time, O(N) space
two ppinters + dp counting:
let dp[i] indicates number of a[x] that x <= i and a[x] <= a[i]
traverse i (front) and j (back) that a[j] < a[i]
then dp[i] = max{dp[j] + 1}
*/
class Solution {
public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] >= 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
