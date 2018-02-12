/*
similar to #256: extend color to k
dp[i][j] = min{dp[i-1].begin(), dp[i-1].end()} + costs[i][j]
*/
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int n = costs.length;
        int k = costs[0].length;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                // paint ith house with color j
                // find min cost solution for prev i-1 houses
                for (int x = 0; x < k; x++) {
                    // skip color j and paint prev i-1
                    if (x != j) {
                        min = Math.min(min, costs[i - 1][x]);
                    }
                }
                // save dp[i][j] to cost[i][j]
                // dp[i][j] = min{dp[i-1][x]} + cost[i][j]
                costs[i][j] += min;
            }
        }

        int min = Integer.MAX_VALUE;
        // find overall min cost
        for (int j = 0; j < k; j++) {
            min = Math.min(min, costs[n - 1][j]);
        }

        return min;
    }
}

/*
optimize to O(N * K):
Record two variables min1 and min2
where min1 is the minimum value, min2 is next to the minimum value.
*/
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int n = costs.length;
        int k = costs[0].length;

        // dp[j] means the min cost for color j
        int[] dp = new int[k];
        int min1 = 0;
        int min2 = 0;

        for (int i = 0; i < n; i++) {
            int oldMin1 = min1;
            int oldMin2 = min2;

            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;

            for (int j = 0; j < k; j++) {
                if (dp[j] != oldMin1 || oldMin1 == oldMin2) {
                    dp[j] = oldMin1 + costs[i][j];
                } else {
                    dp[j] = oldMin2 + costs[i][j];
                }

                if (min1 <= dp[j]) {
                    min2 = Math.min(min2, dp[j]);
                } else {
                    min2 = min1;
                    min1 = dp[j];
                }
            }

        }

        return min1;
    }
}
