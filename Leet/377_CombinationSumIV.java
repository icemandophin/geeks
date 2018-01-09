/*
DP approach:
comb[target] = Sum{comb[target - nums[i]]} => divide to dependent sub problems
Need backtrack if specific route/combination is required
Notice diff from 518: outer loop is for X => build each dp value with all possible
coin combination (with sequence)
*/
class Solution {
    public int combinationSum4(int[] a, int x) {
        if (a == null || a.length == 0) {
            return 0;
        }
        // save one for empty
        int[] dp = new int[x + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; ++i) {
            dp[i] = 0;
            for (int j = 0; j < a.length; ++j) {
                // check subproblems contains a[j]
                if (i >= a[j]) {
                    dp[i] += dp[i - a[j]];
                }
            }
        }

        return dp[x];
    }
}

/*
Recursive approach: same idea as DP, but TLE..
*/
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += combinationSum4(nums, target - nums[i]);
            }
        }
        return res;
    }
}
