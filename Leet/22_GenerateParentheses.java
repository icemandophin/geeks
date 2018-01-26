/*
DFS + backtrack:
dfs per diff between left/right Parenthesis count
find one combination when lc == rc == 0
*/
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        dfs(n, n, "", res);

        return res;
    }
    // given count of left/right Parenthesis count, generate all possible combinations
    private void dfs(int lc, int rc, String cur, List<String> res) {
        if (lc == 0 && rc == 0) {
            res.add(cur);

            return;
        }
        // try to add "("
        if (lc > 0) {
            dfs(lc - 1, rc, cur + "(", res);
        }
        // try to balance ")"
        if (rc > lc) {
            dfs(lc, rc - 1, cur + ")", res);
        }
    }
}
