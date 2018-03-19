/*
stack approch:
traverse from right -> left
when top == "?" => cur is expression
1. pop top 2 elements
2. eval expression and push selected one back to stack
*/
public class Solution {
    public String parseTernary(String expression) {
        Stack<Character> stack = new Stack<Character>();
        int i = expression.length() - 1;

        while (i >= 0) {
            char ch = expression.charAt(i--);

            if (!stack.empty() && stack.peek() == '?') {
                // pop "?"
                stack.pop();

                if (ch == 'T') {
                    // pop 1st and push back after pop 2nd
                    char left = stack.pop();
                    stack.pop();
                    stack.push(left);
                } else {
                    // pop 1st
                    stack.pop();
                }
            } else if (ch != ':') {
                // skip separator
                stack.push(ch);
            }
        }

        return String.valueOf(stack.peek());
    }
}
