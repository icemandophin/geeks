/*
let dp[i] be min coin numbers to make value i
if dp[i] exist, it must comes from dp[i - a[j]]:
dp[i] = Min{dp[i - a[j]] + 1} where j from [1 : n-1]
*/
class Solution {
    public int coinChange(int[] coins, int x) {
        int[] dp = new int[x + 1];
        dp[0] = 0;
        for (int i = 1; i <= x; ++i) {
            // init dp[] - Integer.MAX_VALUE indicate not possible to reach
            dp[i] = Integer.MAX_VALUE;
            // find min dp[i-1] => dp[i] from all coins
            for (int coin : coins) {
                // ensure coin can be part of result
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if (dp[x] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[x];
        }
    }
}
