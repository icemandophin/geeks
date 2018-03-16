/*
regard hole as dest
besides dist[][], rercord min path to hole so far
*/
public class Solution {
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        String[] dir = { "u", "d", "l", "r" };
        int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        String[] result = new String[1];
        int[][] rec = new int[maze.length][maze[0].length];

        for (int i = 0; i < rec.length; i++) {
            Arrays.fill(rec[i], Integer.MAX_VALUE);
        }

	    rec[ball[0]][ball[1]] = 0;
        dfs(maze, hole, ball[0], ball[1], 0, "", result, rec, dir, delta);

        return result[0] == null ? "impossible" : result[0];
    }

    private void dfs(int[][] maze, int[] hole, int i, int j, int count, String curr, String[] result, int[][] rec, String[] dir, int[][] delta) {
        // check if cur path can be min path
        if (count > rec[hole[0]][hole[1]] || count > rec[i][j]) {
            return;
        }

        for (int k = 0; k < delta.length; k++) {
            int x = i;
            int y = j;
            int step = count;
            String path = curr + dir[k];

            while (isValid(maze, x, y, delta[k])) {
                step++;
                x += delta[k][0];
                y += delta[k][1];
                // check if arrive hole during rolling
                if (x == hole[0] && y == hole[1] && step <= rec[x][y]) {
                    if (step < rec[x][y]) {
                        // found shorter path
                        rec[x][y] = step;
                        result[0] = path;
                    } else if (path.compareTo(result[0]) < 0) {
                        // found same length but lexigraphically smaller path
                        result[0] = path;
                    }

                    return;
                }

                rec[x][y] = Math.min(rec[x][y], step);
            }
            // continue dfs for last space
            if (step > count) {
                dfs(maze, hole, x, y, step, path, result, rec, dir, delta);
            }
        }
    }
    // check if (i, j) is in boundary and be space
    private boolean isValid(int[][] maze, int i, int j, int[] diff) {
        int x = i + diff[0];
        int y = j + diff[1];

        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}

/*
BFS approach:
*/
public class Solution {
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        String[] dir = { "u", "d", "l", "r" };
        int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        String result = null;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(ball[0], ball[1], 0, ""));
        int[][] rec = new int[maze.length][maze[0].length];

        for (int i = 0; i < rec.length; i++) {
            Arrays.fill(rec[i], Integer.MAX_VALUE);
        }

	    rec[ball[0]][ball[1]] = 0;

        while (!queue.isEmpty()) {
            Point peek = queue.poll();

            for (int k = 0; k < delta.length; k++) {
                int x = peek.i;
                int y = peek.j;
                int step = peek.step;
                String path = peek.path + dir[k];

                while (isValid(maze, x, y, delta[k])) {
                    step++;
                    x += delta[k][0];
                    y += delta[k][1];

                    if (x == hole[0] && y == hole[1] && step <= rec[x][y]) {
                        if (step < rec[x][y]) {
                            rec[x][y] = step;
                            result = path;
                        } else if (path.compareTo(result) < 0) {
                            result = path;
                        }
                    }

                    rec[x][y] = Math.min(rec[x][y], step);
                }

                // enqueue last space if it is possible to make min path to hole
                if (step > peek.step && step <= rec[hole[0]][hole[1]] && step <= rec[x][y]) {
                    queue.offer(new Point(x, y, step, path));
                }
            }
        }

        return result == null ? "impossible" : result;
    }

    private boolean isValid(int[][] maze, int i, int j, int[] diff) {
        int x = i + diff[0];
        int y = j + diff[1];

        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}

class Point {
    public int i;
    public int j;
    public int step;
    public String path;

    public Point(int i, int j, int step, String path) {
        this.i = i;
        this.j = j;
        this.step = step;
        this.path = path;
    }
}
