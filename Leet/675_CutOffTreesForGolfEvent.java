class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                int height = forest.get(i).get(j);

                if (height > 1) {
                    queue.offer(new int[] { i, j, height });
                }
            }
        }

        int[] idx = new int[2];
        int res = 0;
        int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        while (!queue.isEmpty()) {
            int[] peek = queue.poll();
            int dist = getDist(forest, idx, peek, delta);

            if (dist == -1) {
                return -1;
            } else {
                res += dist;
            }

            idx = peek;
        }

        return res;
    }

    private int getDist(List<List<Integer>> forest, int[] src, int[] dest, int[][] delta) {
        int dist = 0;

        if (src[0] == dest[0] && src[1] == dest[1]) {
            return dist;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(src);
        int m = forest.size();
        int n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        visited[src[0]][src[1]] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            dist++;

            for (int i = 0; i < size; i++) {
                int[] peek = queue.poll();

                for (int[] diff : delta) {
                    int x = peek[0] + diff[0];
                    int y = peek[1] + diff[1];

                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        if (x == dest[0] && y == dest[1]) {
                            return dist;
                        }

                        if (forest.get(x).get(y) > 0) {
                            queue.offer(new int[] { x, y });
                            visited[x][y] = true;
                        }
                    }
                }
            }
        }

        return -1;
    }
}
