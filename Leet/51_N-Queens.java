/*
classic dfs + backtrack:
n queens on N * N grid => one queen on each row
=> each unique soln can be represented as pos[n]
diagonal check: abs(d-ff_x) == abs(diff_y)
*/
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // pos[i] = j => for i-th row the queen is put on j-th col
        int[] pos = new int[n];

        dfs(res, pos, 0);

        return res;
    }

    // figure out possible soln to place queen on row idx
    private void dfs(List<List<String>> res, int[] pos, int idx) {
        int n = pos.length;

        if (idx == n) {
            // add cur soln to res
            List<String> soln = new ArrayList<>();
            for (int p : pos) {
                // convert pos[] to string
                soln.add(putQueen(p, n));
            }
            res.add(soln);

            return;
        }

        // check each col for possible placement
        for (int j = 0; j < n; ++j) {
            if (checkValid(pos, idx, j)) {
                pos[idx] = j;
                // find soln for next row
                dfs(res, pos, idx + 1);
                // backtrack
                pos[idx] = -1;
            }
        }
    }

    // put queen on x-th place of row and return string
    private String putQueen(int x, int n) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            if (i == x) {
                res.append("Q");
            } else {
                res.append(".");
            }
        }

        return res.toString();
    }

    // check if place queen on (i, j) breaks rule
    private boolean checkValid(int[] pos, int i, int j) {
        // only need to check first i positions
        for (int k = 0; k < i; ++k) {
            // should not in same col / diagonal
            if (pos[k] == j || Math.abs(i - k) == Math.abs(j - pos[k])) {
                return false;
            }
        }

        return true;
    }

}
