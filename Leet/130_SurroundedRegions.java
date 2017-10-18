/*
DFS approach
search from all edges and mark 'O' => 'S'
then mark remaining 'O' => 'X'
*/
class Solution {
    public void solve(char[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return;
        }
        int m = a.length;
        int n = a[0].length;
        int i, j;
        // if size < 3, there can't be O surrounded by X
        if (m < 3 || n < 3) {
            return;
        }
        // create move util array
        int[][] mov = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (i = 0; i < m; ++i) {
            // DFS both row boundaries and replace all connected "O" with "S"
            dfs(a, i, 0, mov);
            dfs(a, i, n - 1, mov);
        }
        for (j = 0; j < n; ++j) {
            // DFS both col boundaries and replace all connected "O" with "S"
            dfs(a, 0, j, mov);
            dfs(a, m - 1, j, mov);
        }
        // after DFS from edge all remaining "O"s are not connected
        // traverse and mark all "O" => "X", "S" => "O"
        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                if (a[i][j] == 'O') {
                    a[i][j] = 'X';
                }
                if (a[i][j] == 'S') {
                    a[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] a, int i, int j, int[][] move) {
        if (a[i][j] != 'O') {
            return;
        }
        // mark edge "O" with "S"
        a[i][j] = 'S';
        // dfs and mark current "O"'s neighbors
        for (int[] mov : move) {
            int x = i + mov[0];
            int y = j + mov[1];
            if (x >= 0 && x < a.length && y >= 0 && y < a[0].length) {
                dfs(a, x, y, move);
            }
        }
    }
}
