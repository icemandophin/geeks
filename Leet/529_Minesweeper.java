class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        int[][] move = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1},
                        {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        // get trigger click coordinates
        int x = click[0];
        int y = click[1];

        if (board[x][y] == 'M') {
            // flag mine => game over
            board[x][y] = 'X';
        } else {
            board[x][y] = 'B';
            // BFS neighbors with queue
            Queue<int[]> q = new LinkedList<>();
            q.add(click);

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                x = cur[0];
                y = cur[1];
                int cnt = 0;
                // get cur slot's neighbors
                List<int[]> neighbors = getNeighbor(board, move, x, y);
                for (int[] neighbor : neighbors) {
                    if (board[neighbor[0]][neighbor[1]] == 'M') {
                        cnt++;
                    }
                }

                if (cnt > 0) {
                    // overwrite val to cnt and stop
                    board[x][y] = (char)('0' + cnt);
                } else {
                    // cur slot is surrounded by empty slots => continue BFS
                    for (int[] neighbor : neighbors) {
                        if (board[neighbor[0]][neighbor[1]] == 'E') {
                            board[neighbor[0]][neighbor[1]] = 'B';
                            q.offer(neighbor);
                        }
                    }
                }
            }
        }

        return board;
    }
    // return all valid neighbors of (i, j)
    private List<int[]> getNeighbor(char[][] board, int[][] move, int i, int j) {
        List<int[]> res = new ArrayList<>();

        for (int k = 0; k < move.length; ++k) {
            int x = i + move[k][0];
            int y = j + move[k][1];

            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                res.add(new int[] {x, y});
            }
        }

        return res;
    }
}
