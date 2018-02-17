class Solution {
    public String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();
        // convert from end to top
        while (n > 0) {
            // get offset: 'A' - 0, 'Z' - 25
            int idx = --n % 26;
            // append new at front
            res.insert(0, (char)('A' + idx));
            n /= 26;
        }

        return res.toString();
    }
}
