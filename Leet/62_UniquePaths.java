class Solution {
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        // only one way to access 1st row
        for (int i = 0; i < m; ++i) {
            res[i][0] = 1;
        }
        // only one way to access 1st col
        for (int i = 0; i < n; ++i) {
            res[0][i] = 1;
        }
        // 2 way to reach grid[i][j]: grid[i-1][j] and grid[i][j-1]
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }

        return res[m - 1][n - 1];
    }
}
