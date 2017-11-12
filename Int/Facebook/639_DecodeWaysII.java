/*
dp[i] = dp[i-2]*(# of decoding XY)+dp[i-1]*(# of decoding Y)
use helper method to get # of decodings for Y/XY
*/
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // % N to avoid overflow
        int N = 1000000007;
        int n = s.length();
        // with '*' the number of results become much bigger
        long[] dp = new long[n + 1];
        // traverse start from dp[2] => init dp[0] and dp[1]
        dp[0] = 1;
        dp[1] = num(s.substring(0, 1));
        // traverse
        for (int i = 2; i <= n; ++i) {
            String one = s.substring(i - 1, i);
            String two = s.substring(i - 2, i);
            dp[i] = (dp[i - 1] * num(one) + dp[i - 2] * num(two)) % N;
        }
        // convert back to int type
        return (int)dp[n];
    }
    // get num of decoding for Y/XY
    private int num(String a) {
        char[] ch = a.toCharArray();
        // convert Y
        if (a.length() == 1) {
            if (ch[0] == '*') {
                // 1 to 9
                return 9;
            } else if (ch[0] == '0') {
                return 0;
            } else {
                // 1- 9
                return 1;
            }
        }
        // convert XY
        if (a.equals("**")) {
            // 11 to 26 except 20!!
            // notice * is not 0
            return 15;
        } else if (ch[1] == '*') {
            if (ch[0] == '1') {
                // 11 to 19
                return 9;
            } else if (ch[0] == '2') {
                // 21 to 26
                return 6;
            } else {
                // no match
                return 0;
            }
        } else if (ch[0] == '*') {
            int cur = Character.getNumericValue(ch[1]);
            if (cur <= 6) {
                // 0: 10, 20
                // 1-6: 1*, 20*
                return 2;
            } else {
                // 17 - 19
                return 1;
            }
        } else {
            // cannot use valueOf(), which mismatches "04"
            int ten = Character.getNumericValue(ch[0]);
            if (ten == 0 || ten > 2) {
                // "04" or "90" not valid
                return 0;
            }
            // both digits scenario
            int res = Integer.valueOf(a);
            if (res >= 10 && res <= 26) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
