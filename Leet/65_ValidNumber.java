/*
remove space at head/tail
then validate number, dot, e, sign scenario:
valid number should look like +/- 123.45 e 678
=> set filter/pipeline for each char in str
*/
public class Solution {
    public boolean isNumber(String s) {
        // remove extra space
        s = s.trim();
        int len = s.length();
        // flag if below type has shown
        boolean num = false;
        boolean sign = false;
        boolean e = false;
        boolean point = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if ('0' <= c && c <= '9') {
                // number can come without sign
                // least restriction
                num = true;
            } else if ((c == '+' || c == '-') && !sign && !num && !point) {
                // sign should come first if exist
                // sign is allowed before/after e
                // there should be only one sign
                sign = true;
            } else if (c == '.' && !point && !e) {
                // dot should between num and e (only integer is allowed after e)
                // only one dot allowed
                point = true;
            } else if (c == 'e' && !e && num) {
                // only one e allowed after num
                // no dot is allowed after e
                e = true;
                num = false;
                sign = false;
                point = false;
            } else {
                return false;
            }
        }

        return num;
    }
}
