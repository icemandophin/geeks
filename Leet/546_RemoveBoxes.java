/*
brutal approach:
dp[i][j][k] indicates max point can get from [i : j]
when there are k same numbers on left of i
=> target result is dp[0][n-1][0]
when there exist x in [i : j] that boxes[i] == boxes[x]:
1. remove [i + 1 : x - 1] to make x connects to i
   => get point dp[i + 1][x - 1][0]
2. handle remaining part [x : j]
  => get point dp[x][j][k + 1]
*/
public class Solution {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k <= i; k++) {
                // init: left k numbers connect with i and make points
                dp[i][i][k] = (k + 1) * (k + 1);
            }
        }

        for (int d = 1; d < n; d++) {
            for (int i = 0; i < n - d; i++) {
                int j = i + d;

                for (int k = 0; k <= i; k++) {
                    dp[i][j][k] = (k + 1) * (k + 1) + dp[i + 1][j][0];

                    for (int m = i + 1; m <= j; m++) {
                        if (boxes[i] == boxes[m]) {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i + 1][m - 1][0] + dp[m][j][k + 1]);
                        }
                    }
                }
            }
        }

        return dp[0][n - 1][0];
    }
}

/*
trim with dfs:
directly reuse when dp[i][j][k] has been calculated before
*/
public class Solution {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];

        return dfs(boxes, dp, 0, n - 1, 0);
    }

    public int dfs(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) {
            return 0;
        }

        if (dp[l][r][k] > 0) {
            // reuse prev result
            return dp[l][r][k];
        }
        // find number of same number on left of l
        while (l < r && boxes[l] == boxes[l + 1]) {
            l++;
            k++;
        }
        // handle left part
        dp[l][r][k] = (k + 1) * (k + 1) + dfs(boxes, dp, l + 1, r, 0);
        // handle right part
        for (int i = l + 1; i <= r; i++) {
            if (boxes[i] == boxes[l]) {
                dp[l][r][k] = Math.max(dp[l][r][k], dfs(boxes, dp, l + 1, i - 1, 0) + dfs(boxes, dp, i, r, k + 1));
            }
        }

        return dp[l][r][k];
    }
}
