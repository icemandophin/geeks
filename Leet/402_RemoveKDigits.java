/*
Greedy:
if s[i] > s[i + 1] => delete s[i] makes s[i + 1] take its place
and make smaller value
=> delete k descending elements from MSB to make remaining value min
*/
class Solution {
    public String removeKdigits(String s, int k) {
        StringBuilder res = new StringBuilder(s);

        // search and remove k descending element
        // start from MSB
        for (int i = 0; i < k; ++i) {
            // record desc index
            int idx = 0;
            int n = res.length();

            for (int j = 0; j < n; ++j) {
                // find index of 1st descending element
                // if res is all ascending => remove last / biggest
                if (j == n - 1 || res.charAt(j) > res.charAt(j + 1)) {
                    idx = j;
                    break;
                }
            }
            // remove descending element
            // s[idx + 1] will take the place and make smaller value
            res.deleteCharAt(idx);
        }

        // check if res is empty
        if (res.length() == 0) {
            return "0";
        }

        // remove prefix 0s
        while (res.charAt(0) == '0') {
           if (res.length() == 1) {
                break;
           }

            res.deleteCharAt(0);
        }

        return res.toString();
    }
}
