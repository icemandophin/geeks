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
                    if(stk.isEmpty() || stk.pop() != c)
                    {
                        return false;
                    }
                    break;
                case ']':
                    if(stk.isEmpty() || stk.pop() != c)
                    {
                        return false;
                    }
                    break;
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
