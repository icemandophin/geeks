/*
traverse the string and get longest palindrome that has i or (i, j) as center
in helper method, generate result from center to top/end, only need to check a[i]==a[j]
*/
class Solution {
public String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.length() == 1) {
            return s;
        }
        String res = s.substring(0, 1);
        for (int i = 0; i < s.length(); ++i) {
            // get longest palidrome with i as center (odd)
            String cur = helper(s, i, i);
            if (cur.length() > res.length()) {
                res = cur;
            }
            // get longest palidrome with (i,i+1) as center (even)
            cur = helper(s, i, i+1);
            if (cur.length() > res.length()) {
                res = cur;
            }
        }
    return res;
    }

    // helper method that get longest palindrome with center x/(x,y)
    public String helper(String s, int x, int y) {
        while (x >= 0 && y < s.length() && s.charAt(x) == s.charAt(y)) {
            --x;
            ++y;
        }
        // when exit x is 1 minor and y is 1 bigger
        // hence includes x+1 and excludes y
        return s.substring(x+1, y);
    }
}
