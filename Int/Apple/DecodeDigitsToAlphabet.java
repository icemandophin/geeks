/*
Given a helper function that will return 1 - 'a', 2 - 'b', 3 - 'c'...26 - 'z'
Write a function that will translate input string
Example:
input '1124' -> result = [aabd, ard, qx, qbd]
*/
package Solution;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // Driver method to test Map class
    public static void main(String[] args)
    {
        char[] dict = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'};

        String str = "1124";
        List<String> res = translate(str, dict);
        for (String s : res) {
            System.out.println(s);
        }
    }

    private static List<String> translate(String digits, char[] dict) {
        List<String> res = new ArrayList<>();

        dfs(digits, dict, "", res, 0);

        return res;
    }

    private static void dfs(String digits, char[] dict, String cur, List<String> res, int idx) {
        if (idx == digits.length()) {
            res.add(cur);
            return;
        }
        // match one digit
        char one = digits.charAt(idx);
        int i = Character.getNumericValue(one) - 1;
        if (i >= 0 && i <= 9) {
            char ci = dict[i];
            dfs(digits, dict, cur + ci, res, idx + 1);
        }
        // try to match 2 digits
        if (idx < digits.length() - 1) {
            String two = digits.substring(idx, idx + 2);
            int j = Integer.valueOf(two) - 1;
            if (j >= 10 && j <= 25) {
                char cj = dict[j];
                dfs(digits, dict, cur + cj, res, idx + 2);
            }
        }
    }
}
