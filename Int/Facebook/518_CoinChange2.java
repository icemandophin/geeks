/*
dp[i][j] indicates number of combinations that make value j with first i coins
dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0)
notice the i muast be outer loop: build full value  with min coin, then add coin number
*/
class Solution {
    public int change(int x, int[] a) {
        int n = a.length;
        int[][] dp = new int[n + 1][x + 1];
        dp[0][0] = 1;
        // build dp with coin number increase
        for (int i = 1; i <= n; ++i) {
            // build value 0 with no coin
            dp[i][0] = 1;
            for (int j = 1; j <= x; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (j >= a[i - 1]) {
                    dp[i][j] += dp[i][j - a[i - 1]];
                }
            }
        }

        return dp[n][x];
    }
}

/*
optimize space to O(n)
*/
class Solution {
    public int change(int x, int[] a) {
        int n = a.length;
        // dp[i] is number of ways to make value i
        int[] dp = new int[x + 1];
        dp[0] = 1;
        for (int coin : a) {
            // find all dp[] that can make with cur coin
            for (int i = coin; i <= x; ++i) {
                // cur coin can build all values from coin to x
                // add it to dp[]
                dp[i] += dp[i - coin];
            }
        }

        return dp[x];
    }
}
