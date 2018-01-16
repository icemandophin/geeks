/*
DP: record if s[0 : i) and p[0: j) can match
for '?' or char match => match s[0 : i - 1) - p[0: j - 1)
for '*' => match p[0, j - 1) with s[0, i),s[0, i - 1)...s[0, 0)
=> dp[i][j] = dp[i][j-1] || dp[i-1][j-1] || ... || dp[0][j-1]
and dp[i-1][j] = dp[i-2][j-1] || dp[i-3][j-1] || ... || dp[0][j-1]
=> for '*' dp[i][j] = dp[i-1][j] || dp[i][j-1]
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //dp[i][j] records if s.substring(0, i) and s.substring(0, j) can match
        boolean[][] dp = new boolean[m + 1][n + 1];
        // init dp[][]
        // '*' can match anything => dp[0][j] only depend on prev dp
        dp[0][0] = true;
        for (int j = 1; j <= n; j++){
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char ch = p.charAt(j - 1);

                if (ch == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (s.charAt(i - 1) == ch || ch == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}

/*
two pointer approach:
1. when '*':
(1) if j < n-1 then start = i and ++j (mark i)
else return true (last wildcard matches everything)
(2) while i < n && s[i] != p[j] ++i
2. current char match or '?' => ++i ++j
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int idx = -1;
        // star marks 1st char after '*'
        int star = -1;

        while (i < s.length()) {
            if (j < p.length() && p.charAt(j) == '*') {
                // start 2nd index to skip char in s for '*'
                idx = i;
                // record cur '*' index
                star = j++;
            } else if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                // compare logic
                // if current char match => move on
                i++;
                j++;
            } else if (star != -1) {
                // keep move i forward when s[i] and p[star + 1] not match
                // j need to reset to star + 1 for next iteration
                i = ++idx;
                j = star + 1;
            } else {
                // hit mismatch before '*'
                return false;
            }
        }
        // move j forward if there is remaining '*'
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        // check if p has been fully matched
        return j == p.length();
    }
}
