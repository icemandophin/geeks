/*
Recursive approach: use hashmap to reduce repeated calculation
*/
public class Solution {
    // save substring comparison result
    // if s1, s2 are scrumble, insert hash(s1#s2) = true
    HashMap<String, Boolean> hash = new HashMap<String, Boolean>();

    public boolean isScramble(String s1, String s2) {
        // check corner case
        if (s1.equals(s2)) {
            return true;
        }
        // memory search - directly return calculated result
        if (hash.containsKey(s1 + "#" + s2)) {
            return hash.get(s1 + "#" + s2);
        }
        // scramble should have same length
        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        if (n == 1) {
            return s1.charAt(0) == s2.charAt(0);
        }
        // if s1 and s2 are scrumbles, there must exist a partition (cut at k)
        // that make s1(k)~s2(k) or s1(k)~s2(n-k) scrumble
        for (int k = 1; k < n; ++k) {
            if ((isScramble(s1.substring(0, k), s2.substring(0, k)) &&
                isScramble(s1.substring(k, n), s2.substring(k, n))) ||
                (isScramble(s1.substring(0, k), s2.substring(n-k, n)) &&
                isScramble(s1.substring(k, n), s2.substring(0, n-k)))) {
                hash.put(s1 + "#" + s2, true);
                return true;
            }
        }
        hash.put(s1 + "#" + s2, false);
        return false;
    }
}
