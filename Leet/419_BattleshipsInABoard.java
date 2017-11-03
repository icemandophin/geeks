/*
optimize: count number of ship head
define ship head as NW most node
=> all other nodes in a ship must have left/up node as 'X'
*/
class Solution {
    public int countBattleships(char[][] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int m = a.length;
        int n = a[0].length;
        int res = 0;
        // traverse and count ship head
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 'X' &&
                    ((i == 0 || a[i - 1][j] == '.') &&
                     (j == 0 || a[i][j - 1] == '.'))) {
                    res++;
                }
            }
        }
        return res;
    }
}

/*
DFS approach: similiar to number of islands
O(M*N) time, O(M*N) space (unless modify a[][] is allowed)
*/
class Solution {
    public int countBattleships(char[][] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int m = a.length;
        int n = a[0].length;
        boolean[][] vi = new boolean[m][n];
        for (boolean[] k : vi) {
            Arrays.fill(k, false);
        }
        int res = 0;
        // traverse map
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 'X' && !vi[i][j]) {
                    // dfs neighbors and mark all of them
                    dfs(a, vi, i, j);
                    // count this one ship
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] a, boolean[][] vi, int x, int y) {
        if (x >= a.length || x < 0 || y >= a[0].length || y < 0 ||
           vi[x][y] || a[x][y] != 'X') {
            return;
        }
        // mark cur position (x, y)
        vi[x][y] = true;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int i = 0; i < 4; ++i) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            dfs(a, vi, cx, cy);
        }
    }
}
