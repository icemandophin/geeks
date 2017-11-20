/*
given array p[N] that p[i] = x means price of rope with length i is x
long rope can be cut into smaller pieces and sell separately
find max profit for rope with length k

DP: profit[n] = Math.max(profit[n],profit[n-j]+price[j])
*/
class Solution {
    int maxProfit(int[] p, int k) {
        int n = p.length;
        // dp[i] is the max profit for rope with length i
        int[] dp = new int[k + 1];
        for (int i = 0; i <= k; ++i) {
            // consider all possible cuts with p[]
            for (j = n - 1; j >= 0; --j) {
                if (i >= j) {
                    dp[i] = Math.max(dp[i], dp[i - j] + p[j]);
                }
            }
        }

        return dp[k];
    }
}
