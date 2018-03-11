/*
winning check: single player filled one row/col/diagonal/antiDiagonal
naive approach: check win for each move => O(N^2)
Optimize:
mark one player with 1, the other with -1
record total sum of row[], col[], diag, anti-diag during move
winning check: sum of row/col/diag/anti-diag is N or -N
*/
public class TicTacToe {
    private int[] rows;
    private int[] cols;
    // there is only one diag/anti-diag => array not needed
    private int diagonal;
    private int antiDiagonal;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int size = rows.length;
        // use symbol to distinguish player, num to record filled number
        int val = player == 1 ? 1 : -1;
        // add player val to row/col
        rows[row] += val;
        cols[col] += val;
        // check and add player val to diag
        if (row == col) {
            diagonal += val;
        }
        // check and add player val to anti-diag
        if (row + col == size - 1) {
            antiDiagonal += val;
        }

        if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size  || Math.abs(antiDiagonal) == size) {
            return player;
        } else {
            return 0;
        }
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
