/*
DP approach: O(N)
let dp[i] be max money that can get from first i houses
depend on whether to rob i-th house: dp[i] = max{dp[i-1], dp[i-2]+a[i]}
optimize space complexity: O(1)
notice dp[i] only depend on dp[i-1] and dp[i-2] - rolling list[3] will be enough
*/
class Solution {
    public int rob(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int even = 0;
        int odd = 0;
        int i;
        for (i =0; i < a.length; ++i) {
            // optimized from: dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1])
            if (i % 2 == 0) {
                even += a[i];
                even = Math.max(even, odd);
            }
            else {
                odd += a[i];
                odd = Math.max(even, odd);
            }
        }
        return Math.max(even, odd);
    }
}
