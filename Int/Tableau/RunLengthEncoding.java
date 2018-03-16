public class Solution {
    public String lengthEncoding(String str) {
        int n = str.length();
        int[] cnt = new int[26];
        StringBuilder res = new StringBuilder();

        // build cnt mapping
        for (int i = 0; i < n; ++i) {
            cnt[str.charAt(i) - 'a']++;
        }

        for (int i = 0; i < n; ++i) {
            char ch = str.charAt(i);
            if (cnt[ch - 'a'] > 0) {
                res.append(ch);
                res.append(cnt[ch - 'a']);
                cnt[ch - 'a'] = 0;
            }
        }

        return res.toString();
    }
}
