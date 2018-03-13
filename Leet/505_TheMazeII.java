/*
memorized dfs: record min step from start to each space during dfs
*/
public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int[][] rec = new int[maze.length][maze[0].length];

        for (int i = 0; i < rec.length; i++) {
            Arrays.fill(rec[i], Integer.MAX_VALUE);
        }

        dfs(maze, destination, start[0], start[1], 0, rec, delta);

        return rec[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : rec[destination[0]][destination[1]];
    }

    private void dfs(int[][] maze, int[] hole, int i, int j, int count, int[][] rec, int[][] delta) {
        // step to cur space has already exceeded min step fo far
        // no need to continue
        if (count >= rec[hole[0]][hole[1]] || count >= rec[i][j]) {
            return;
        }

        rec[i][j] = count;
        // check dest
        if (i == hole[0] && j == hole[1]) {
            return;
        }

        for (int k = 0; k < delta.length; k++) {
            int x = i;
            int y = j;
            int step = count;

            while (isValid(maze, x, y, delta[k])) {
                x += delta[k][0];
                y += delta[k][1];
                // add step count for each move
                step++;
            }

            dfs(maze, hole, x, y, step, rec, delta);
        }
    }

    private boolean isValid(int[][] maze, int i, int j, int[] diff) {
        int x = i + diff[0];
        int y = j + diff[1];

        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}

/*
BFS approach:
for each position, roll out in 4 directions and find last spaces
if cur visit provides smaller step count => update rec and enqueue
*/
public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start[0], start[1], 0));
        int[][] rec = new int[maze.length][maze[0].length];

        for (int i = 0; i < rec.length; i++) {
            Arrays.fill(rec[i], Integer.MAX_VALUE);
        }

        rec[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            Point peek = queue.poll();

            for (int k = 0; k < delta.length; k++) {
                int x = peek.i;
                int y = peek.j;
                int step = peek.step;

                while (isValid(maze, x, y, delta[k])) {
                    x += delta[k][0];
                    y += delta[k][1];
                    step++;
                }
                // if cur space has exceeded step count to dest => no need to continue
                // update min path if step of this visit is smaller
                if (step < rec[destination[0]][destination[1]] && step < rec[x][y]) {
                    rec[x][y] = Math.min(rec[x][y], step);
                    queue.offer(new Point(x, y, step));
                }
            }
        }

        return rec[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : rec[destination[0]][destination[1]];
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

    public Point(int i, int j, int step) {
        this.i = i;
        this.j = j;
        this.step = step;
    }
}
