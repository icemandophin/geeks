/*
divide and solve:
1. count freq of each char
2. for char that appear less than k times:
(1) use as separator and divide str
(2) recur find max len in each str
*/
class Solution {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        if (n < k) {
            // str too short to hold
            return 0;
        }

        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            cnt[s.charAt(i) - 'a']++;
        }
        // if all char appear more than k times then skip and return s length
        for (int i = 0; i < 26; ++i) {
            // find unqualified
            if (cnt[i] > 0 && cnt[i] < k) {
                char ch = (char)('a' + i);
                String[] strs = s.split(Character.toString(ch));
                int res = 0;

                for (String str : strs) {
                    res = Math.max(res, longestSubstring(str, k));
                }

                return res;
            }
        }

        return n;
    }
}
