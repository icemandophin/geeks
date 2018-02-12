/*
DFS:
start from each gate room and check neighbor rooms:
1. if neighbor room has smaller distance => stop
2. if neighbor distance is larger => update neighbor and dfs from there
*/
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        int row = rooms.length;
        int col = rooms[0].length;
        int[][] delta = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0, delta);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int i, int j, int dist, int[][] delta) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || dist > rooms[i][j]) {
            return;
        }

        rooms[i][j] = dist;

        for (int[] diff : delta) {
            int x = i + diff[0];
            int y = j + diff[1];

            dfs(rooms, x, y, dist + 1, delta);
        }
    }
}

/*
BFS:
enqueue (x, y) of all gate rooms
for each gate BFS neighbor and update diatance of empty room
BFS ensure empty always get distance from closet gate 
*/
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        int row = rooms.length;
        int col = rooms[0].length;
        int[][] delta = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Queue<int[]> queue = new LinkedList<int[]>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] peek = queue.poll();
            int i = peek[0];
            int j = peek[1];

            for (int[] diff : delta) {
                int x = i + diff[0];
                int y = j + diff[1];

                if (x >= 0 && x < row && y >= 0 && y < col && rooms[x][y] == Integer.MAX_VALUE) {
                    rooms[x][y] = rooms[i][j] + 1;
                    queue.offer(new int[] { x, y });
                }
            }
        }
    }
}
