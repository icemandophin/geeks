/*
similar to #139 word break:
dp[j] is true if dp[i] is true and s[i : j-1] is in dict
*/
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        // add short words first for concatenating longer words
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for (String word : words) {
            if (check(word, set)) {
                res.add(word);
            }
            // add word to dict
            set.add(word);
        }

        return res;
    }

    private boolean check(String s, Set<String> set) {
        int n = s.length();

        if (n == 0) {
            return false;
        }

        // partition and match substring
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                // trim: if dp[j] is false => no need to check further j value
                if (!dp[j]) {
                    continue;
                } if (set.contains(s.substring(j, i))) {
                    // if exist dp[j] that makes dp[i] true => no need to check other j
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
