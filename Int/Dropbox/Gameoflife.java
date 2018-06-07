public class GameofLife {
    public void GameOfLife(int[][] board)
    {
        int r = board.length;
        int c = board[0].length;
        for (int i = 0; i <= r; i++)
        {
            for (int j = 0; j <= c; j++)
            {
                int lives = calNumberofLives(board, i, j, r , c);

                if (board[i][j]==1 && (lives < 2 || lives > 3))
                board[i][j] = 2;
                    else if (board[i][j]==0 && lives == 3)
                board[i][j] = 3;
            }
        }
        for (int i = 0; i <= r; i++)
        {
            for (int j = 0; j <= c; j++)
            {
                if (board[i][j]%2 == 1)
                board[i][ j] = 1;
                    else
                {
                    board[i][j] = 0;
                }
            }
        }

    }

    public int calNumberofLives(int[][] board, int i, int j, int r, int c)
    {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, r - 1); x++)
        {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, c - 1); y++)
            {
                if (x != i || y != j)
                {
                    if (board[x][y] == 1 || board[x][ y] == 2)
                    lives++;
                }

            }
        }
        return lives;
    }
    public void GameofLife2()
    {
        int line =0;

        while(line<Integer.MAX_VALUE)
        {
            int[][] board = new int[3][];
            for(int i = line; i<line+3; line++)
            {
               // board[i][] = APIReadLine(Matrix[i][]);read 3 line

            }
            int[][] updatedBoard = GameOfLife2(board);
            for(int i = line; i<line+2; line++)
            {
                //Matrix[i][] = APIWriteLine(board[i][]); write 2 line
            }
            line+=2;
        }

    }

    public int[][] GameOfLife2(int[][] board)
    {
        int r = board.length;
        int c = board[0].length;
        for (int i = 0; i <= r; i++)
        {
            for (int j = 0; j <= c; j++)
            {
                int lives = calNumberofLives(board, i, j, r , c);

                if (board[i][j]==1 && (lives < 2 || lives > 3))
                    board[i][j] = 2;
                else if (board[i][j]==0 && lives == 3)
                    board[i][j] = 3;
            }
        }
        for (int i = 0; i <= r; i++)
        {
            for (int j = 0; j <= c; j++)
            {
                if (board[i][j]%2 == 1)
                    board[i][ j] = 1;
                else
                {
                    board[i][j] = 0;
                }
            }
        }
        return board;
    }

}
