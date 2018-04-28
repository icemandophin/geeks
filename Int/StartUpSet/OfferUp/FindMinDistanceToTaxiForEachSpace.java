package Solution;

import java.util.*;
/*
Taxi distance
distance = abs(x2 - x1) + abs(y2 - y1)

Bitmap (n x m) - 0 - black, 1 - white
For each pixel on bitmap find the distance to closest white pixel.

4 x 3
0 0 1 0
1 1 0 0
0 0 0 0

Result
1 1 0 1
0 0 1 2
1 1 2 3
*/
class Solution {
    public static void main(String[] args) {
        int[][] input = new int[][]{
            {0, 0, 1, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 0}};

        int[][] res = closestDistance(input);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int[][] closestDistance(int[][] bitMap) {
        int m = bitMap.length;
        int n = bitMap[0].length;
        int[][] res = new int [m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (bitMap[i][j] == 1) {
                    res[i][j] = 0;
                    bfs(bitMap, res, i, j);
                }
            }
        }

        return res;
    }

    private static void bfs(int[][] bitMap, int[][] res, int x, int y) {
        int m = bitMap.length;
        int n = bitMap[0].length;

        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        int dis = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int k = 0; k < size; ++k) {
                Pair cur = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int cx = cur.x + move[i][0];
                    int cy = cur.y + move[i][1];

                    if (cx >= 0 && cx < m && cy >= 0 && cy < n && bitMap[cx][cy] == 0) {
                        if (res[cx][cy] > dis) {
                            res[cx][cy] = dis;
                            q.offer(new Pair(cx, cy));
                        }
                    }
                }
            }

            dis++;
        }
    }
}

class Pair {
    public int x;
    public int y;

    public Pair(int i, int j) {
        x = i;
        y = j;
    }
}
