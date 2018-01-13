/*
stack approach:
1. each time get operator, always pop top 2 elements and calculate
then push res into stack
2. if it is digit then directly push to stack
*/
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        // for "-" and "/" ensure sequence
        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                // push digit directly
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.peek();
    }
}
