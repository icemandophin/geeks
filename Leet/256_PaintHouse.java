/*
let dp[i][j] represent min cost to pain first i houses with j colors
if paint ith house with R - need to paint prev i-1 houses with i-1 colors
with min cost
=> total = Min{dp[i-1][j - 1]} + cost[i][R]
*/
public class Solution {
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }

        int len = costs.length;

        for (int i = 1; i < len; i++) {
            costs[i][0] += Math.min(costs[i - 1][1],costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0],costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0],costs[i - 1][1]);
        }
        // find global min in last row of dp[][]
        return Math.min(Math.min(costs[len - 1][0], costs[len - 1][1]), costs[len - 1][2]);
    }
}
