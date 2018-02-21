/*
money that guarantee a win => Max{cost among all scenarios (target number from 1 - n)}
=> best soln = Min{ Max{cost among all scenarios} }

DP approach:
dp[i][j] is min cost for [i, j], x is a guess in [i, j]
after guess(x) we know next search should be left/right
consider worst scenario => cost(x) = Max{dp[i][x - 1], dp[x + 1][j]} + x
dp[i][j] = Min{cost(x)} (try to guess from each x)
*/
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 2];

        // increase len between [s, e]
        for (int l = 1; l < n; ++l) {

            // move start point
            for (int s = 1; s + l <= n; ++s) {
                int e = s + l;
                int min = Integer.MAX_VALUE;

                // find min guarantee_cost for [s, e]
                // try from every possible x
                for (int x = s; x <= e; ++x) {
                    int cur = Math.max(dp[s][x - 1], dp[x + 1][e]) + x;
                    min = Math.min(min, cur);
                }
                dp[s][e] = min;
            }
        }

        return dp[1][n];
    }
}

/*
recursive: same idea
*/
public class Solution {
    public int getMoneyAmount(int n) {
        int[][] rec = new int[n + 1][n + 1];

        return getMoneyAmount(1, n, rec);
    }

    private int getMoneyAmount(int start, int end, int[][] rec) {
        if (start >= end) {
            return 0;
        }

        if (rec[start][end] > 0) {
            return rec[start][end];
        }

        int min = Integer.MAX_VALUE;

        for (int i = start; i <= end; i++) {
            min = Math.min(min, i + Math.max(getMoneyAmount(start, i - 1, rec), getMoneyAmount(i + 1, end, rec)));
        }

        rec[start][end] = min;

        return min;
    }
}
