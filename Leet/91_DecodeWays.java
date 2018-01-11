/*
two digit XY can decode：9<XY<=26
sigle digit Y can decode：Y != '0'
add dp[i - 1], dp[i - 2] to dp[i] with condition
*/
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        // dp[i] is # of decode ways for substring [0 : i]
        int[] dp = new int[n + 1];
        // for empty string, only one way to decode
        dp[0] = 1;
        // no match for single '0'
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        // traverse
        for (int i = 2; i <= n; ++i) {
            // convert Y value to int
            int one = Integer.valueOf(s.substring(i - 1, i));
            if (one >= 1 && one <= 9) {
                // can decode dp[i-1] + last one
                dp[i] += dp[i - 1];
            }
            // convert XY to int
            int two = Integer.valueOf(s.substring(i - 2, i));
            if (two >= 10 && two <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
