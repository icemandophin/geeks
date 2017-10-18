/*
dp[i][j] represent # of steps to convert first i chars of word1 to first j chars of word2
Consider converting w1(i) to w2(j) from previous steps:
1. (corner case) if w1[i] == w2[j]:
only need to convert w1(i-1) to w2(j-1) => dp[i-1][j-1]
2. w1[i] != w2[j]:
there are 3 approaches that available(from last move's perspective)
(1) still convert w1(i-1) to w2(j-1), then replace w1[i] to w2[j] => dp[i-1][j-1] + 1
(2) convert w1(i-1) to w2(j), plus remove w1[i] at the end => dp[i-1][j] + 1
(3) convert w1(i) to w2(j-1), plus add w2[j] to the end => dp[i][j-1] + 1
Since we have 3 ops in total, above cover all scenarios of w1(i) -> w2(j)
hence dp[i][j] = min{dp[i-1][j-1], dp[i-1][j], dp[i][j-1]} + 1
*/
class Solution {
    public int minDistance(String w1, String w2) {
        int m = w1.length();
        int n = w2.length();
        // dp[i][j] means convert w1[0:i-1] to w2[0:j-1]
        int[][] dp = new int[m+1][n+1];
        int i, j;
        // init dp[][] - pure add/delete op takes # of size to complete
        for (i = 0; i < m + 1; ++i) {
            dp[i][0] = i;
        }
        for (i = 0; i < n + 1; ++i) {
            dp[0][i] = i;
        }
        // DP general case
        for (i = 1; i < m + 1; ++i) {
            for (j = 1; j < n + 1; ++j) {
                if (w1.charAt(i-1) == w2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}
