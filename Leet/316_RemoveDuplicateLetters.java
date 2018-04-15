/*
Greedy: for each insert, always pop larger and repeated char in res
then add cur char
build hashmap and record freq for each char
keep hashset to quick check chars exist in res

reference:
https://leetcode.com/discuss/75529/c-simple-solution-easy-understanding
*/
class Solution {
    public String removeDuplicateLetters(String s) {
        StringBuilder res = new StringBuilder();
        // cnt for each char
        int[] cnt = new int[26];
        int n = s.length();
        // record chars that added to res
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            cnt[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            cnt[ch - 'a']--;

            if (set.contains(ch)) {
                continue;
            }

            // while last char ce in cur res is larger than ch
            // and it is not the last time ce appears => remove ce (can add later)
            // then add ch
            int len = res.length();
            while (len > 0 &&
                   ch < res.charAt(len - 1) &&
                   cnt[res.charAt(len - 1) - 'a'] > 0) {
                // remove last char
                set.remove(res.charAt(len - 1));
                res.setLength(len - 1);
                // update length
                len = res.length();
            }
            res.append(ch);
            set.add(ch);
        }

        return res.toString();
    }
}
