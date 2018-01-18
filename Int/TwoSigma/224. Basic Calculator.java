/*
handle per input char:
1. digit: can be multiple digit num => add all following digits
2. +/-: set sign to 1/-1
3. (: push cur res and sign to stack
4. ): pop 1st top (which should be sign) and calculate with 2nd top
*/
class Solution {
    public int calculate(String s) {
        int n = s.length();
        int sign = 1;
        int res = 0;
        Stack<Integer> sk = new Stack<>();

        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            // got digit
            if (Character.isDigit(ch)) {
                // build number from continuous digits
                int sum = ch - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                // add cur num to res
                res += sum * sign;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                // push left operand and operator/sign to stack
                sk.push(res);
                sk.push(sign);
                res = 0;
                sign = 1;
            } else if (ch == ')') {
                // right operand +/- left operand
                res = res * sk.pop() + sk.pop();
            }
        }

        return res;

    }
}
