/*
DP approach: similar to backpack problem
dp[i]: min # of cuts that partition s[0: i]
dp[i] = min{dp[j] + 1}, where j < i and
substring [j : i] is palindrome
*/
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] res = new int[n];

        // check substring palindrom
        for (int k = 0; k < n; ++k) {
            // check odd palindrome
            check(s, dp, k, k);
            // check even palindrome
            check(s, dp, k, k + 1);
        }
        for (int i = 0; i < n; ++i) {
            // s[i : i] is always palindrome
            res[i] = i;
            for (int j = 0; j <= i; ++j) {
                if (dp[j][i] == true) {
                    // if [0 : i] is palindrome => no need to cut [0 : i]
                    // otherwise find min cut from sub-problem
                    res[i] = Math.min(res[i], j == 0 ? 0: res[j - 1] + 1);
                }
            }
        }

        return res[n - 1];
    }
    // check palindrome whose center is [top : end]
    private void check(String s, boolean[][] dp, int top, int end) {
        while (top >= 0 && end < s.length() && s.charAt(top) == s.charAt(end)) {
            dp[top][end] = true;
            top--;
            end++;
        }
    }
}
