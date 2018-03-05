import java.util.*;
// System.out.println("res: ");

public class Solution {
	public static void main(String[] args) {
		String input = "(+ 1 2 4 (+ (+ (- 1 3) 3) 4 (+ 5 4 5 6)))";

		System.out.println(PolishNotation.eval(input));
	}
}

class PolishNotation {
	public static int eval(String str) {
		int res = 0;
		char[] s = str.toCharArray();
		Stack<Cal> sk = new Stack<>();

		for (int i = 0; i < s.length; ++i) {
			if (s[i] == '(') {
				int sign = s[++i] == '+' ? 1 : -1;
				// mark as no val coming in yet
				sk.push(new Cal(sign, Integer.MAX_VALUE));
			} else if (s[i] >= '0' && s[i] <= '9') {
				// get cur number
				int j = i;
				while (j < str.length() && (str.charAt(j) >= '0' && str.charAt(j) <= '9')) {
					j++;
				}
				int num = Integer.parseInt(str.substring(i, j));

				Cal cur = sk.peek();
				if (cur.val == Integer.MAX_VALUE) {
					// init with 1st val in cur layer
					cur.val = num;
				} else {
					cur.val += cur.sign * num;
				}
			} else if (s[i] == ')') {
				Cal top = sk.pop();
				if (!sk.isEmpty()) {
					Cal upper = sk.peek();
					if (upper.val == Integer.MAX_VALUE) {
						upper.val = top.val;
					} else {
						upper.val += upper.sign * (top.val);
					}
				} else {
					// got final result
					res = top.val;
					break;
				}
			}
		}
		// if there is invalid input like '(' mismatch
		// return 0
		return res;
	}
}

class Cal {
	public int sign;
	public int val;

	public Cal(int s, int v) {
		sign = s;
		val = v;
	}
}
