/*
max length of sequence => DP:
max length of substring[i : j] palindrome is dp[i][j]
if s[i] == s[j] => dp[i][j] = dp[i+1][j-1] + 2
else dp[i][j] = max {dp[i+1][j], dp[i][j-1]}
*/
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // traverse from end to top
        for (int i = n - 1; i >= 0; --i) {
            // single char is always palindrome
            dp[i][i] = 1;
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}

/*
optimize space: notice dp[i][x] only depend on dp[i][y] and dp[i+1][z]
=> i % 2 to decrease space to O(N)
*/
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[2][n];
        // traverse from end to top
        for (int i = n - 1; i >= 0; --i) {
            // single char is always palindrome
            dp[i % 2][i] = 1;
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i % 2][j] = dp[(i + 1) % 2][j - 1] + 2;
                } else {
                    dp[i % 2][j] = Math.max(dp[(i+1) % 2][j], dp[i % 2][j-1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}

/*
memory search version:
*/
public class Solution {
    public int longestPalindromeSubseq(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    private int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j)      return 0;
        if (i == j)     return 1;

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }
}
