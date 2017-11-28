/*
similiar DFS like #200 number of islands
distinguish diff island: calculate coordinate diff between (x, y) and upper-left root
*/
class Solution {
    private static int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // save edge combination for each island
        Set<List<List<Integer>>> islands = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // island will contain all edge diff for cur island
                List<List<Integer>> island = new ArrayList<>();
                if (dfs(i, j, i, j, grid, m, n, island)) {
                    islands.add(island);
                }
            }
        }

        return islands.size();
    }
    // return true if new island is found, save edge diff of island
    private boolean dfs(int i0, int j0, int i, int j, int[][] grid, int m, int n, List<List<Integer>> island) {
        if (i < 0 || m <= i || j < 0 || n <= j || grid[i][j] <= 0) {
            return false;
        }
        // save relative diftance between each point and root/upper-left point
        island.add(Arrays.asList(i - i0, j - j0));
        grid[i][j] *= -1;
        for (int d = 0; d < 4; d++) {
            dfs(i0, j0, i + delta[d][0], j + delta[d][1], grid, m, n, island);
        }
        return true;
    }
}
