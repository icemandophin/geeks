/*
operator priority:
'+': push num to stack
'-': push -num to stack
'*': pop top and * num, then push
'/': pop top and / num, then push
notice * or / is operated before push to stack => operator priority
+/- is pushed to stack first => calculate in stack at last
*/
class Solution {
    public int calculate(String s) {
        int n = s.length();
        // marks cur operator
        char sign = '+';
        int res = 0;
        Stack<Integer> sk = new Stack<>();

        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                // build number from continuous digits
                int sum = ch - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                // got operator -> operate per sign
                if (sign == '+') {
                    sk.push(sum);
                } else if (sign == '-') {
                    sk.push(-sum);
                } else if (sign == '*') {
                    sk.push(sk.pop() * sum);
                } else {
                    sk.push(sk.pop() / sum);
                }
             } else if (ch != ' ') {
                sign = ch;
            }
        }
        // handle remaining add/minus elements
        for (int x : sk) {
            res += x;
        }

        return res;
    }
}
