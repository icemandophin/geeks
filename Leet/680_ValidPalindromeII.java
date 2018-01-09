/*
when 1st mismatch found: a[i] != a[j]
just check if remaining part can be palindrome
two possibilities: s.substring(i, j) and s.substring(i + 1, j + 1)
*/
class Solution {
    public boolean validPalindrome(String s) {
        int low = 0;
        int high = s.length() - 1;
        while (low < high) {
             if (s.charAt(low) == s.charAt(high)) {
                ++low;
                --high;
            } else {
                // check if remaining part can be pure palindrome
                return isPalindrome(s, low + 1, high) || isPalindrome(s, low, high - 1);
            }
        }

        return true;
    }
    // check pure palindrome
    private boolean isPalindrome (String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                ++low;
                --high;
            } else {
                return false;
            }
        }

        return true;
    }
}
