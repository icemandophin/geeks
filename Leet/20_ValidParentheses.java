/*
push symmatric "right" part to stack for "left" inputs
check stack top for "right" inputs
consider stack empty
*/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<Character>();

        for(char c : s.toCharArray())
        {
            switch(c)
            {
                case '(':
                    stk.push(')');
                    break;
                case '{':
                    stk.push('}');
                    break;
                case '[':
                    stk.push(']');
                    break;
                case ')':
                case ']':
                case '}':
                    if(stk.isEmpty() || stk.pop() != c)
                    {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }

        return stk.isEmpty();
    }
}
