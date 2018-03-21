class Solution {
    public boolean wordBreak(String s, List<String> dict) {
        int n = s.length();
        if (s == null || n == 0) {
            return false;
        }

        Set<String> set = new HashSet<>(dict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                String cur = s.substring(j, i);
                if (dp[j] && set.contains(cur)) {
                    dp[i] = true;
                }
            }
        }

        return dp[n];
    }
}
