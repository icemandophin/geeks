/*
always remove ')' with min index to make str valid (or left > right)
then recursively solve
*/
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        // enum of ( )
        char[] par = new char[] {'(', ')'};
        removeRight(s, res, 0, 0, par);

        return res;
    }
    // x is the start index of cur substring
    // y is start index that delete ')' is allowed
    private void removeRight(String s, List<String> res, int x, int y, char[] par) {
        for (int cnt = 0, i = x; i < s.length(); ++i) {
            // count number of '('
            if (s.charAt(i) == par[0]) {
                cnt++;
            }
            if (s.charAt(i) == par[1]) {
                cnt--;
            }
            // if '(' is more than ')' => fine
            if (cnt >= 0) {
                continue;
            }
            // remove one ')' in prefix, then recur remaining
            for (int j = y; j <= i; ++j) {
                if (s.charAt(j) == par[1] && (j == y || s.charAt(j - 1) != par[1])) {
                    // remove s[j] and check remaining
                    removeRight(s.substring(0, j) + s.substring(j + 1, s.length()), res, i, j, par);
                }
            }

            return;
        }

        // above ensure no more ')'
        // reverse s and run again to handle '('
        String revs = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            // last run is for ')' => need another run
            removeRight(revs, res, 0, 0, new char[] {')', '('});
        } else {
            // last run is for '('
            res.add(revs);
        }
    }
}
