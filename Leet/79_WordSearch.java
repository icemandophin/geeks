/*
DFS search: find 1st char, then try to match remaining word in all directions recursively
1. {1, 0}, {-1, 0}, {0, 1}, {0, -1} to indicate 4 directions
2. within DFS, first ensure (i, j) in boundary and value match current char (marked by index)
3. recursively DFS 4 directions for sub-string (index+1)
*/

public class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean [m][n];
        int[][] move = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int i, j;

        for (i=0; i<m; ++i)
        {
            for (j=0;j<n;++j)
            {
                if (dfs(board, word, i, j, visited, 0, move))
                {
                    return true;
                }
            }
        }

        return false;
    }
    // DFS try to match word[i : N-1] in baord, starting from b[i][j] 
    private boolean dfs(char[][] board, String word, int i, int j, boolean[][] visited, int index, int[][] move)
    {
        if (index == word.length())
        {
            return true;
        }

        if ((i<0) || (i>=board.length) || (j<0) || (j>=board[0].length) || (visited[i][j]) || (board[i][j]!=word.charAt(index)))
        {
            return false;
        }

        visited[i][j] = true;

        int k, x, y;

        for (k=0; k<move.length; ++k)
        {
            x = i + move[k][0];
            y = j + move[k][1];

            if (dfs(board, word, x, y, visited, index+1, move))
            {
                return true;
            }
        }

        visited [i][j] = false;

        return false;
    }
}
