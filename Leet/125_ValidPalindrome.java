/*
two pointer approach:
1. consider empty string
2. skip non-alphanumeric chars
*/
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int top = 0;
        int end = s.length() - 1;
        while (top < end) {
            if (!Character.isLetterOrDigit(s.charAt(top))) {
                ++top;
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                --end;
            } else if (Character.toLowerCase(s.charAt(top)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            } else {
                ++top;
                --end;
            }
        }

        return true;
    }
}
