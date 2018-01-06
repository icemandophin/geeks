/*
DFS + Backtrack:
1. each single char s[i] must be palindrome
2. if s[0 : i] is palindrome => DFS partition s[i + 1 : n - 1] and add to res
*/
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        int n = s.length();
        // dp[i][j] is true if substring [i : j] is palindrome
        boolean[][] dp = new boolean[n][n];
        // check and set dp substring for palindrome
        for (int i = 0; i < n; ++i) {
            // check odd palindrome
            check(s, dp, i, i);
            // check even palindrome
            check(s, dp, i, i + 1);
        }
        // backtrack s for all palindrome
        dfs(s, dp, 0, new ArrayList<String>(), res);

        return res;
    }
    // check palindrome whose center is [top : end]
    private void check(String s, boolean[][] dp, int top, int end) {
        while (top >= 0 && end < s.length() && s.charAt(top) == s.charAt(end)) {
            dp[top][end] = true;
            top--;
            end++;
        }
    }
    // dfs for all palindrome partitions of substring [i : n-1]
    private void dfs(String s, boolean[][] dp, int idx, List<String> cur, List<List<String>> res) {
        // cur partition has completed => add to res and return
        if (idx == s.length()) {
            res.add(new ArrayList<String>(cur));
            return;
        }
        // partition substring [idx, n - 1]
        for (int i = idx; i < s.length(); ++i) {
            // if substring [idx, i] is palindrome
            // then partition and dfs sub-problem [i + 1, n - 1]
            if (dp[idx][i] == true) {
                cur.add(s.substring(idx, i + 1));
                // dfs substring [i + 1 : n - 1]
                dfs(s, dp, i + 1, cur, res);
                // backtrack to try other sub-partition
                cur.remove(cur.size() - 1);
            }
        }
    }
}
