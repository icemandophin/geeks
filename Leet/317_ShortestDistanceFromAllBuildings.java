/*
?
*/
public class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int row = grid.length;
        int col = grid[0].length;
        // total[i][j] is the distance between space (i, j) and all buildings
        int[][] total = new int[row][col];
        int[][] delta = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // val marks empty space
        int val = 0;
        int min = -1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    min = -1;
                    int dist = 0;
                    // BFS queue contains 2D coordinate
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[] { i, j });

                    while (!queue.isEmpty()) {
                        int len = queue.size();
                        dist++;

                        for (int k = 0; k < len; k++) {
                            int[] peek = queue.poll();

                            for (int[] diff : delta) {
                                int x = peek[0] + diff[0];
                                int y = peek[1] + diff[1];

                                if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == val) {
                                    grid[x][y]--;
                                    total[x][y] += dist;
                                    min = min == -1 ? total[x][y] : Math.min(min, total[x][y]);
                                    queue.offer(new int[] { x, y });
                                }
                            }
                        }
                    }

                    val--;
                }
            }
        }

        return min;
    }
}
