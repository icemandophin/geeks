/*
hashset for quick check substring in dict
no need to get all partitions, just Yes/No
=> boolean dp[i] refers whether s[0 : i) can be partitioned
dp[j] = dp[i] + s[i : j-1] is in dict
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        if (s == null || n == 0) {
            return false;
        }
        // init hashset with List f words
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        // empty string can be partitioned by no word
        dp[0] = true;

        // check if s[i, j) can be partitioned
        for (int j = 1; j <= n; ++j) {
            for (int i = 0;i < j; ++i) {
                String cur = s.substring(i, j);
                if (dp[i] && dict.contains(cur)) {
                    dp[j] = true;
                }
            }
        }

        return dp[n];
    }
}
