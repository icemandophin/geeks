/*
1. P[i][j] = P[i - 1][j - 1], if p[j - 1] != '*' && (s[i - 1] == p[j - 1] || p[j - 1] == '.');
2. P[i][j] = P[i][j - 2], if p[j - 1] == '*'
and the pattern repeats for 0 times;
3. P[i][j] = P[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'), if p[j - 1] == '*'
and the pattern repeats for at least 1 times.
*/
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // record if s[0 : i) and p[0 : j) can match
        boolean[][]  dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // init for '*'
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char ch = p.charAt(j - 1);
                // for '*' => skip '*' and char before '*'
                if (ch == '*') {
                    dp[i][j] = dp[i][j - 2];
                    // further '.' scenario
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] |= dp[i - 1][j];
                    }
                } else if (s.charAt(i - 1) == ch || ch == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}
