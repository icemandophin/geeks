/*
DFS approach:
for each land find, DFS 4 adjacent cells recursively and add count of island
to avoid repeated count, in DFS all adjacent land is set to sea
*/

class Solution {
    private int m;
    private int n;

    public int numIslands(char[][] grid)
    {
        int res = 0;
        n = grid.length;
        if(n == 0)
        {
            return res;
        }

        m = grid[0].length;
        int i, j;

        for (i=0; i<n; ++i)
        {
            for (j=0; j<m; ++j)
            {
                if (grid[i][j] == '1')
                {
                    DFSMarking(grid, i, j);
                    ++res;
                }
            }
        }

        return res;
    }

    private void DFSMarking(char[][] grid, int i, int j)
    {
        if((i<0) || (j<0) || (i>=n) || (j>=m) || (grid[i][j]!='1'))
        {
            return;
        }

        grid[i][j] = '0';
        DFSMarking(grid, i-1, j);
        DFSMarking(grid, i+1, j);
        DFSMarking(grid, i, j-1);
        DFSMarking(grid, i, j+1);
    }
}
