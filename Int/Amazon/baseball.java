/*
operation always related to prev 1/2 results => stack push/pop
for each operation, calculate result of this op and push to stack
later pop out for R/W
*/
package Amazon;
import java.util.*;

class Solution {
    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) {
		   return 0;
	   }
        Stack<Integer> stk = new Stack<>();
        // since "+" requires 2 pop from stk
        // push 0s to stack as buffer
        stk.push(0);
        stk.push(0);

        int res = 0;
        // traverse ops
        for (String op : ops) {
            int cur;
            switch (op) {
                case "+":
                    int prev1 = stk.pop();
                    int prev2 = stk.pop();
                    cur = prev1 + prev2;
                    res += cur;
                    // first push previously borrowed result back
                    stk.push(prev2);
                    stk.push(prev1);
                    // always save result of cur step to stk
                    stk.push(cur);
                    break;
                case "X":
                    int last = stk.pop();
                    cur = last * 2;
                    res += cur;
                    // same as above
                    stk.push(last);
                    stk.push(cur);
                    break;
                case "Z":
                    // remove it from result also
                    res -= stk.pop();
                    break;
                default:
                    // handle pure digit scenario
                    cur = Integer.parseInt(op);
                    res += cur;
                    // SL da fa hao!
                    stk.push(cur);
                    break;
            }
            if (stk.isEmpty()) {
                stk.push(0);
                stk.push(0);
            }
        }
        return res;
    }
}
