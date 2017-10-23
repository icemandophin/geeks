/*
Given 2-d array of "sharpness" values, fine the path from left to right which
has the highest minimum sharpness:
route from left->right, each time can go from (i, j) to (i, j+1)/(i-1, j+1)/(i+1, j+1)
eg:
.5 .7 .2
.7 .5 .8
.9 .1 .5
path: .7->.7->.8 => return 7
*/

/*
1. keep recording max min value of each route in dp[]:
for each item in the col:
dp[i][j] = Min{Max{dp[i][j-1], dp[i-1][j-1], dp[i+1][j-1]}, a[i][j]}
2. after last col find max value of dp[]
*/
public class solution{
    public int MinimumPhotoSharpness(int[][] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int res = Integer.MIN_VALUE;
        int i, j;
        int m = a.length;
        int n = a[0].length;
        // rolling array for size optimization
        int[][] dp = new int[m][2];
        // init dp with 1st col
        for (i = 0; i < m; ++i) {
            dp[i][0] = a[i][0];
        }
        // scan each col and find min
        for (j = 1; j < n; ++j) {
            for (i = 0; i < m; ++i) {

                int cur = dp[i][(j - 1) % 2];
                if (i != 0) {
                    cur = Math.max(cur, dp[i - 1][(j - 1) % 2]);
                }
                if (i != m - 1) {
                    cur = Math.max(cur, dp[i + 1][(j - 1) % 2]);
                }
                // check whether max prev dp or a[i][j]
                // is the min value in current route
                dp[i][j % 2] = Math.min(cur, a[i][j]);
            }
        }
        // dp contains min value, find max one
        for (i = 0; i < m; ++i) {
            res = Math.max(res, dp[i - 1][(j - 1) % 2]);
        }
        return res;
    }
}
