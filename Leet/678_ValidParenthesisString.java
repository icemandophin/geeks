/*
two stack approach: one for '(' the other for '*'
'(' or '*': push
')':
1. sk1 and sk2 empty: no way to match - false
2. sk1 not empty: pop sk1 match with '('
   else pop sk2 to match with '*'

loop end: if sk1 not empty => use sk2 to match sk1
if sk1 still not empty => return false

Notice: need to push index instead of char to detect scenario like: "*("
which is NOT valid
*/
class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '(') {
                left.push(i);
            } else if (c == '*') {
                star.push(i);
            } else {
                if (left.isEmpty() && star.isEmpty()) {
                    // impossible to match
                    return false;
                } else if (!left.isEmpty()) {
                    // always try to match with '(' first
                    left.pop();
                } else {
                    // match with '*'
                    star.pop();
                }
            }
        }

        // try to match remaining '(' with '*'
        while (!star.isEmpty() && !left.isEmpty()) {
            if (left.peek() > star.peek()) {
                // '*' can only match '(' that comes before itself
                return false;
            } else {
                star.pop();
                left.pop();
            }
        }

        if (left.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

/*
recursive approach: keep tracking num of '('
1. for '(' or ')' - update count
2. for '*' - try to use as '(', ')' and '*' then resolve sub-problem
*/
class Solution {
    public boolean checkValidString(String s) {
        return checkValidString(s, 0, 0);
    }

    private boolean checkValidString(String s, int i, int count) {
        if (count < 0) {
            // exist ')' that cannot match
            return false;
        }

        if (i == s.length()) {
            return count == 0;
        }

        char ch = s.charAt(i);

        if (ch == '(') {
            return checkValidString(s, i + 1, count + 1);
        } else if (ch == ')') {
            return checkValidString(s, i + 1, count - 1);
        } else {
            // '*' can match as '(', ')' or '*'
            // check all possibilities - return true if any one works
            return checkValidString(s, i + 1, count) || checkValidString(s, i + 1, count + 1) || checkValidString(s, i + 1, count - 1);
        }
    }
}

/*
2 cnt approach:
low: # of '(' when use '*' as ')'
high: # of ')' when use '*' as '('
*/
class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char ch: s.toCharArray()) {
            low += ch == '(' ? 1 : -1;
            high += ch != ')' ? 1 : -1;

            if (high < 0) {
                // cannot match extra ')'
                return false;
            }
            // to many '(' to match
            low = Math.max(low, 0);
        }

        return low == 0;
    }
}
