/*
same approach for #51:
add global cnt for each soln instead of saving it
*/
class Solution {
    public int totalNQueens(int n) {
        int[] res = new int[1];
        // pos[i] = j => for i-th row the queen is put on j-th col
        int[] pos = new int[n];

        dfs(res, pos, 0);

        return res[0];
    }

    // figure out possible soln to place queen on row idx
    private void dfs(int[] res, int[] pos, int idx) {
        int n = pos.length;

        if (idx == n) {
            res[0]++;

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
