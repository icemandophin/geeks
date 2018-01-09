/*
for each land a[i][j] == 1
only when the land is on top-left/bottom-right or
neighbor cube (like left) has no land, add cont for associated edge (like left edge)
*/
class Solution {
    public int islandPerimeter(int[][] g) {
        if (g == null || g.length == 0 || g[0].length == 0) {
            return 0;
        }
        int m = g.length;
        int n = g[0].length;
        int res = 0;
        // traverse
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] == 1) {
                    // check top edge
                    if (i == 0 || g[i - 1][j] == 0) {
                        ++res;
                    }
                    // check left edge
                    if (j == 0 || g[i][j - 1] == 0) {
                        ++res;
                    }
                    // check bottom edge
                    if (i == m - 1 || g[i + 1][j] == 0) {
                        ++res;
                    }
                    // check right edge
                    if (j == n - 1 || g[i][j + 1] == 0) {
                        ++res;
                    }
                }
            }
        }

        return res;
    }
}
