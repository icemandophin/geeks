/*
follow up of #36:
1. build similar flag for row/col/cube and fill with existing numbers
2. DFS + backtrack for all possible solution:
for each empty slot, try to fill 1 - 9 and DFS sub problem
roll back if sub problem not working
*/

public class Solution {
    public void solveSudoku(char[][] board) {
        int N = 9;
        boolean[][] row = new boolean[N][N];
        boolean[][] col = new boolean[N][N];
        boolean[][] sq = new boolean[N][N];
        // populate ref array refore resolve sudoku
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != '.') {
                    int val = board[i][j] - '1';
                    int k = i / 3 * 3 + j / 3;
                    row[i][val] = col[j][val] = sq[k][val] = true;
                }
            }
        }
        // DFS + backtrack
        dfs(board, row, col, sq);
    }

    private boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean[][] sq) {
        // traversal of entire board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    int k = i / 3 * 3 + j / 3;
                    // try to fill 1 - 9 for each empty slot
                    for (int val = 0; val < 9; val++) {
                        // check rule
                        if (!row[i][val] && !col[j][val] && !sq[k][val]) {
                            // fill slot
                            board[i][j] = (char)('1' + val);
                            // register new added value
                            row[i][val] = col[j][val] = sq[k][val] = true;
                            // next level of DFS will pass only if
                            // satisfy all pre-processed numbers + filled val
                            if (dfs(board, row, col, sq)) {
                                return true;
                            }
                            // backtrack
                            board[i][j] = '.';
                            row[i][val] = col[j][val] = sq[k][val] = false;
                        }
                    }
                    // no way to fill b[i][j] according to rule
                    return false;
                }
            }
        }
        // all slots have been filled
        return true;
    }
}
