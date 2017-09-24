/*
let dp[i][j] represent size of max square that take m[i][j] as right-lower corner
dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
*/
class Solution {
    public int maximalSquare(char[][] a) {
        if (a.length == 0 || a == null) {
            return 0;
        }
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        int[][] dp = new int[m][n];
        int i, j;
        // init 1st row and col of dp
        for(i = 0; i < m; i++) {
            dp[i][0] = a[i][0] - '0';
            res = Math.max(res, dp[i][0]);
        }
        for(i = 0; i < n; i++) {
            dp[0][i] = a[0][i] - '0';
            res = Math.max(res, dp[0][i]);
        }
        // if a[i][j] == '1' and its up, left and up-left element also makes square
        // then dp[i][j] makes square, which is 1 bigger than the min size of prev squares
        for(i = 1; i < m; i++) {
            for(j = 1; j < n; j++) {
                if (a[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
                else {
                    // fail to form new square with a[i][j]
                    dp[i][j] = 0;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }
}
