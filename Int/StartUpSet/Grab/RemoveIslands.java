package Solution;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[][] input = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };

        Island test = new Island();
        test.removeIsland(input);

        for (int[] a : input) {
            System.out.println(Arrays.toString(a));
        }
    }
}

class Island {
    private static int[][] mov = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    public void removeIsland(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; ++i) {
            mark(a, visited, i, 0);
            mark(a, visited, i, n - 1);
        }

        for (int j = 0; j < n; ++j) {
            mark(a, visited, 0, j);
            mark(a, visited, m - 1, j);
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(a, visited, i, j);
            }
        }
    }

    private void mark(int[][] a, boolean[][] visited, int x, int y) {
        int m = a.length;
        int n = a[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || a[x][y] == 0) {
            return;
        }

        visited[x][y] = true;

        for (int k = 0; k < mov.length; ++k) {
            int i = x + mov[k][0];
            int j = y + mov[k][1];

            mark(a, visited, i, j);
        }
    }

    private void dfs(int[][] a, boolean[][] visited, int x, int y) {
        int m = a.length;
        int n = a[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || a[x][y] == 0) {
            return;
        }

        visited[x][y] = true;
        a[x][y] = 0;

        for (int k = 0; k < mov.length; ++k) {
            int i = x + mov[k][0];
            int j = y + mov[k][1];

            dfs(a, visited, i, j);
        }
    }
}


