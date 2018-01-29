/*
stack approach:
a[i] == '(' => push
a[i] == ')':
1. cur stack is empty => no match => reset substring start
2. cur stack has one left => perfect match => curLen = i - start + 1
3. cur stack has more than one left => not fully matched yet
=> update cur matched length i - sk.top()
*/
public class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() <= 1) {
            return 0;
        }
        // record index of '('
        Stack<Integer> sk = new Stack<>();
        int res = 0;
        // record start index of cur match
        int start = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                sk.push(i);
            } else if (s.charAt(i) == ')') {
                if (sk.isEmpty()) {
                    // no match - reset start to next possible
                    start = i + 1;
                } else {
                    // match cur ')'
                    sk.pop();

                    if (sk.isEmpty()) {
                        // cur match ends here - update max length
                        res = Math.max(res, i - start + 1);
                    } else {
                        // match ongoing - update res with cur matched length
                        res = Math.max(res, i - sk.peek());
                    }
                }
            }
        }

        return res;
    }
}

/*
dp appoach:
dp[i] refers to max length for substring s[0 : i)
dp[i] = dp[j - 1] + dp[i - 1] + 2
where j is the index before start '(' of dp[i - 1]
and dp[j - 1] is valid substring in[0 : j - 1) that connect to dp[i - 1]
*/
public class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() <= 1) {
            return 0;
        }

        int res = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); ++i) {
            // dp[i] = 0 for s[i] == '('
            if (s.charAt(i) == ')') {
                // dp[i - 1] is max length in s[0 : i - 2]
                // j is the index before actual start '(' in s[0 : i - 2]
                // which is i - 2 - dp[i - 1] + 1
                int j = i - 1 - dp[i - 1];
                if (j >= 0 && s.charAt(j) == '(') {
                    // j can match s[i] and extend dp[i]
                    dp[i] = 2 + dp[i - 1] + (j > 0 ? dp[j - 1] : 0);
                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
