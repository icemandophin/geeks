public class Solution {
    public String shortestPalindrome(String s) {
        for (int end = s.length() - 1; end >= 0; end--) {
            if (isPalindrome(s, 0, end)) {
                return new StringBuilder(s.substring(end + 1)).reverse().append(s).toString();
            }
        }

        return s;
    }

    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            } else {
                low++;
                high--;
            }
        }

        return true;
    }
}
