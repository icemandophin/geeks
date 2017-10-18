/*
traverse from end:
1. find 1st non-space char
2. count and add 1st space(if exist)
*/

class Solution {
    public int lengthOfLastWord(String s) {
        int res = 0;
        int tail = s.length() - 1;

        while((tail >= 0) && (s.charAt(tail) == ' '))
        {
            --tail;
        }

        while((tail >= 0) && (s.charAt(tail) != ' '))
        {
            ++res;
            // need below to jump out of loop for single word scenario
            --tail;
        }

        return res;
    }
}
