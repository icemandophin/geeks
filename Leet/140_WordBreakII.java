/*
when s/dict is large, direct check substring in hashset will TLE => need pruning
pre-check if str can be divided before DFS
*/
public class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // add dict to hashset
        Set<String> set = new HashSet(wordDict);
        int len = s.length();
        // dp[i] indicates if s[0 : i - 1] can be broke
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        List<String> result = new ArrayList<>();

        if (dp[len]) {
            dfs(s, set, 0, "", result);
        }

        return result;
    }

    private void dfs(String s, Set<String> dict, int idx, String cur, List<String> res) {
        if (idx == s.length()) {
            // add cur approach
            res.add(cur);

            return;
        }

        for (int i = idx + 1; i <= s.length(); ++i) {
            String cut = s.substring(idx, i);
            if (dict.contains(cut)) {
                // notice cur para is passed as new string not ref
                // hence no need for cur.add/remove before/after DFS
                dfs(s, dict, i, cur + (cur.length() > 0 ? " " : "") + cut, res);
            }
        }
    }
}
