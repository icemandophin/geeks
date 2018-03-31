/*
DFS + memorization:
*/
class Solution {
    public int longestIncreasingPath(int[][] a) {
        int res = 0;
        if (a.length == 0 || a[0].length == 0) {
            return res;
        }

        int m = a.length;
        int n = a[0].length;
        // record max path length starting from (i, j)
        int[][] dp = new int[m][n];
        // move util array
        int[][] move = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res = Math.max(res, dfs(a, dp, move, i, j));
            }
        }

        return res;
    }
    // calculate and save max path length for each grid
    // start from (i, j) then search neighbors
    private int dfs(int[][] a, int[][] dp, int[][] move, int i, int j) {
        if (dp[i][j] > 0) {
            // return saved result
            return dp[i][j];
        }
        // init dp[i][j] - every grid is single srray itself
        dp[i][j] = 1;

        for (int[] d : move) {
            // move 4 directions
            int x = i + d[0];
            int y = j + d[1];
            // dfs for ascending neighbor
            if (x >= 0 && x < a.length && y >= 0 && y < a[0].length && a[x][y] > a[i][j]) {
                dp[i][j] = Math.max(dp[i][j], dfs(a, dp, move, x, y) + 1);
            }
        }

        return dp[i][j];
    }

}
