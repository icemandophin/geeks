class Solution {
    public boolean isValidSudoku(char[][] board) {
        // based on rule board size must <= 9
        int N = 9;
        boolean[][] row = new boolean[N][N];
        boolean[][] col = new boolean[N][N];
        // need convert (i, j) to cube location [0 : 8]
        // => 3 * (i / 3) + j / 3
        boolean[][] sq = new boolean[N][N];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    // convert '7' to index 7 - 1 = 6
                    int num = board[i][j] - '1';
                    // k is the index of cube cur element belongs to
                    int k = i / 3 * 3 + j / 3;
                    // detect conflict and register cur val
                    if (row[i][num] || col[j][num] || sq[k][num]) {
                        return false;
                    } else {
                        row[i][num] = col[j][num] = sq[k][num] = true;
                    }
                }
            }
        }

        return true;
    }
}
