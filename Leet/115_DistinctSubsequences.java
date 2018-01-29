/*
DP approach: O(M*N)
dp[i][j]: # of subsequence in s[i] that match t[j]
by default dp[i][j] = dp[i-1][j] (match t using just s[0 : i - 1])
if s[j] = t[j] then it is possible to add new match as long as
s[0 : i-1] and t[0 : j-1] can match => dp[i][j] += dp[i-1][j-1]
*/
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (j == 0) {
                    // when t == "" only one subsequence can match
                    // which is ""
                    dp[i][j] = 1;
                } else if (i == 0) {
                    // when s == "", t is valid
                    // no way to make subsequence that match t
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] += dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
