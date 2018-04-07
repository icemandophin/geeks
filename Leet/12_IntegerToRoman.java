/*
start from larger roman char
each time take the first value < num and append char to res
*/
class Solution {
    public String intToRoman(int x) {
        StringBuilder res = new StringBuilder();
        String[] key = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] val = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        for (int i = 0; i < val.length; ++i) {
            while (x >= val[i]) {
                res.append(key[i]);
                x -= val[i];
            }
        }

        return res.toString();
    }
}

/*
direct mapping: integer range 1 - 3999
=> build mapping for thousand, hundred, ten and one
=> then map thousand, hundred, ten and one bit of num to roman char
O(1) time
*/
class Solution {
    public String intToRoman(int num) {
        String[] M = { "", "M", "MM", "MMM" };
        String[] C = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String[] X = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String[] I = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

        int mb = num / 1000;
        int cb = (num / 100) % 10;
        int xb = (num / 10) % 10;
        int ib = num % 10;

        return M[mb] + C[cb] + X[xb] + I[ib];
    }
}
