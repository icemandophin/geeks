/*
DP approach:
if devide subproblem as dp[i, j] - difficult to build dp[0, N-1] from dp[i, j]
define dp[0, i]: if dp > 0 add with a[i], else keep a[i] only
compare dp with current max in each iteration
Optimize: only dp[i] and dp[i-1] is used during each iteration
=> make rolloing array and mod iteration
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        // optimize with rolling array
        int[] dp = new int[2];
        dp[0] = nums[0];
        int res = dp[0];
        int i;

        for(i = 1; i < len; ++i)
        {
            dp[i % 2] = nums[i] + (dp[(i-1) % 2] > 0? dp[i-1] : 0);
            res = Math.max(res, dp[i % 2]);
        }

        return res;
    }
}
