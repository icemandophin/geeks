/*
regex split approach:
?=X: match str whose suffix contains X
[-+]: represent - or +
steps:
1. divide by "="
2. parse left/right with regex
3. calculate and convert to ax = b format

eg: x+5-2x =6-3x
=> left: x+5-2x right: 6-3x
=> regex: {x, +5, -2x}, {6, -3x}
=> calculate: a = 2, b = 1

1. divide by "="
2. parse and convert to ax = b format with regex
*/
class Solution {
    public String solveEquation(String equation) {
        String[] str = equation.split("=");
        // parse each part with regex
        int[] left = regex(str[0]);
        int[] right = regex(str[1]);
        // get a, b of ax = b
        int a = left[0] - right[0];
        int b = right[1] - left[1];

        if (a == 0 && b == 0) {
            return "Infinite solutions";
        } else if (a == 0) {
            return "No solution";
        } else {
            return "x=" + b / a;
        }
    }
    // parse string with +/-
    // return [a, b] for ax + b
    private int[] regex(String s) {
        int[] res = new int[2];
        // split s where there is +/-
        // ?= => suffix match => cut right before sign
        String[] strs = s.split("(?=[-+])");

        for (String str : strs) {
            if (str.equals("x") || str.equals("+x")) {
                res[0] += 1;
            } else if (str.equals("-x")) {
                res[0] -= 1;
            } else if (str.contains("x")) {
                // there is digit before x => convert to int
                res[0] += Integer.parseInt(str.substring(0, str.indexOf("x")));
            } else {
                // convert pure digit to int
                res[1] += Integer.parseInt(str);
            }
        }

        return res;
    }
}
