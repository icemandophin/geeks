/*
BFS approach:
1. init queue with start position
2. poll cur position -> roll to last space in four directions
3. check if position is destination
4. mark visited and enqueue sapce
*/
public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] peek = queue.poll();

            for (int[] diff : delta) {
                int i = peek[0];
                int j = peek[1];

                while (isValid(maze, i, j, diff)) {
                    i += diff[0];
                    j += diff[1];
                }

                if (i == destination[0] && j == destination[1]) {
                    return true;
                }

                if (!visited[i][j]) {
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }

        return false;
    }

    private boolean isValid(int[][] maze, int i, int j, int[] diff) {
        int x = i + diff[0];
        int y = j + diff[1];

        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}

/*
DFS approach:
1. for each start position check dest and mark as visited
2. check neighbors and roll out 4 directions to last space
3. dfs from last spaces 
*/
public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        return dfs(maze, destination, start[0], start[1], visited, delta);
    }

    private boolean dfs(int[][] maze, int[] destination, int i, int j, boolean[][] visited, int[][] delta) {
        if (visited[i][j]) {
            return false;
        }

	visited[i][j] = true;

        if (i == destination[0] && j == destination[1]) {
            return true;
        }

        for (int[] diff : delta) {
            int x = i;
            int y = j;

            while (isValid(maze, x, y, diff)) {
                x += diff[0];
                y += diff[1];
            }

            if (dfs(maze, destination, x, y, visited, delta)) {
                return true;
            }
        }

        return false;
    }

    private boolean isValid(int[][] maze, int i, int j, int[] diff) {
        int x = i + diff[0];
        int y = j + diff[1];

        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}
