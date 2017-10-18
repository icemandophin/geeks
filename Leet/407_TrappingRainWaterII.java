class Cell {
    public int x, y, h;

    public Cell() {}

    public Cell(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }
}
// define comparator for cell type
class CellComp implements Comparator<Cell> {
    @Override
    public int compare(Cell a, Cell b) {
        if (a.h > b.h) {
            return 1;
        }
        else if (a.h == b.h) {
            return 0;
        }
        else {
            return -1;
        }
    }
}

public class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(new CellComp());
        // mark visited cell
        boolean[][] mark = new boolean[m][n];
        // util array for traversing neighbor cells
        int[] dx = new int[] {0, 0, 1, -1};
        int[] dy = new int[] {1, -1, 0, 0};
        int i;
        int res = 0;

        // insert 4 outmost boundaries into the minHeap/PQ
        for (i = 0; i < m; ++i) {
            minHeap.offer(new Cell(i, 0, heightMap[i][0]));
            mark[i][0] = true;
            minHeap.offer(new Cell(i, n-1, heightMap[i][n-1]));
            mark[i][n-1] = true;
        }
        for (i = 0; i < n; ++i) {
            minHeap.offer(new Cell(0, i, heightMap[0][i]));
            mark[0][i] = true;
            minHeap.offer(new Cell(m-1, i, heightMap[m-1][i]));
            mark[m-1][i] = true;
        }
        // start BFS from adjacent cells of lowest one
        while (!minHeap.isEmpty()) {
            // get current lowest cell
            Cell cur = minHeap.poll();
            // traverse neighbor cells
            for (i = 0; i < 4; ++i) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];

                if (x < m && x >= 0 && y < n && y >= 0 && !mark[x][y]) {
                    // mark neighbor cell as visited, and add it to PQ for getting next lowest cell
                    mark[x][y] = true;
                    minHeap.offer(new Cell(x, y, Math.max(cur.h, heightMap[x][y])));
                    // if neighbor cell is lower, then water can be filled in
                    res += Math.max(0, cur.h - heightMap[x][y]);
                }
            }
        }
        return res;
    }
}
