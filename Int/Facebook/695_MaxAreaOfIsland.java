class Solution {
    private int m;
    private int n;

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;

        n = grid.length;
        if(n == 0) {
            return res;
        }
        m = grid[0].length;

        int i, j;
        for (i=0; i<n; ++i) {
            for (j=0; j<m; ++j) {
                if (grid[i][j] == 1) {
                    // get size of cur island and update res
                    res = Math.max(res, DFSMarking(grid, i, j));
                }
            }
        }

        return res;
    }

    private int DFSMarking(int[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0) {
            return 0;
        }
        // add cnt and update res
        int cnt = 1;
        // reser cur land
        grid[i][j] = 0;
        // search neighbors
        cnt += DFSMarking(grid, i-1, j);
        cnt += DFSMarking(grid, i+1, j);
        cnt += DFSMarking(grid, i, j-1);
        cnt += DFSMarking(grid, i, j+1);

        return cnt;
    }
}
