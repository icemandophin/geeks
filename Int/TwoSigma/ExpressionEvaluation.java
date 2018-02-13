public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        if (expression == null) {
            throw new IllegalArgumentException();
        }

        Stack<Integer> num = new Stack<Integer>();
        Stack<String> opr = new Stack<String>();
        String list = "+-*/";

        for (String cur : expression) {
            if (cur.equals("(")) {
                opr.push(cur);
            }
            else if (cur.equals(")")) {
                while (!opr.peek().equals("(")) {
                    calculate(num, opr);
                }

                opr.pop();
            }
            else if (list.contains(cur)) {
                while (!opr.empty() && !opr.peek().equals("(") && priority(opr.peek(), cur)) {
                    calculate(num, opr);
                }

                opr.push(cur);
            }
            else {
                num.push(Integer.parseInt(cur));
            }
        }

        while (!opr.empty()) {
            calculate(num, opr);
        }

        return num.empty() ? 0 : num.peek();
    }

    private void calculate(Stack<Integer> num, Stack<String> opr) {
        int b = num.pop();
        int a = num.pop();
        String sign = opr.pop();

        if (sign.equals("+")) {
            num.push(a + b);
        }
        else if (sign.equals("-")) {
            num.push(a - b);
        }
        else if (sign.equals("*")) {
            num.push(a * b);
        }
        else if (sign.equals("/")) {
            num.push(a / b);
        }
        else {
            throw new RuntimeException(sign);
        }
    }

    private boolean priority(String a, String b) {
        if (a.equals("*") || a.equals("/")) {
            return true;
        }
        else if (b.equals("+") || b.equals("-")) {
            return true;
        }
        else {
            return false;
        }
    }
}
