/*
DP approach: O(M*N)
for space between 2 walls: W [0] E 0 0 E E 0 W
only calculate row/col enemy number of 1st grid on the right/above, like [0]
=> avoid repeated calculation of row enemy in each space
*/
public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int max = 0;
        int m = grid.length;
        int n = grid[0].length;
        int row = 0;
        int[] col = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 'W') {
                    if (j == 0 || grid[i][j - 1] == 'W') {
                        row = getRowCount(grid, i, j);
                    }

                    if (i == 0 || grid[i - 1][j] == 'W') {
                         col[j] = getColCount(grid, i, j);
                    }

                    if (grid[i][j] == '0') {
                        max = Math.max(max, row + col[j]);
                    }
                }
            }

        }

        return max;
    }
    // count # of enemy on the right of a[i][j] until wall/end
    private int getRowCount(char[][] grid, int i, int j) {
        int count = 0;

        while (j < grid[0].length && grid[i][j] != 'W') {
            if (grid[i][j++] == 'E') {
                count++;
            }
        }

        return count;
    }
    // count # of enemy below a[i][j] until wall/end
    private int getColCount(char[][] grid, int i, int j) {
        int count = 0;

        while (i < grid.length && grid[i][j] != 'W') {
            if (grid[i++][j] == 'E') {
                count++;
            }
        }

        return count;
    }
}
