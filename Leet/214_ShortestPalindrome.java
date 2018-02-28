/*
any str can become palindrome by adding extra chars on left of str
if str[0 : i] is palindrome => append reverse of str[i + 1 : n - 1] on left
makes it palindrome
=> find longest palindrome str[0 : i] for min overall length
*/
public class Solution {
    public String shortestPalindrome(String s) {
        // check from longest substring to find longest palindrome
        for (int end = s.length() - 1; end >= 0; end--) {
            if (isPalindrome(s, 0, end)) {
                // reverse non-palindrome part and add to left of s
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
