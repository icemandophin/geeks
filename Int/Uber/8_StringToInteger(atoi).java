class Solution {
    public int myAtoi(String str) {
        int sign = 1;
        long val = 0;
        int n = str.length();
        int i = 0;
        // skip space
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }
        // detect sign
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            if (str.charAt(i) == '-') {
                sign = -1;
            }

            i++;
        }
        // str => int
        // check value scope for each add
        // only convert char that represent digit
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            int d = sign * (str.charAt(i) - '0');
            val = val * 10 + d;
            // scope check
            if (val > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (val < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int)val;
    }
}
