/*
Boggle game:
Implement a method that finds if a given word is present in boggle board by returning true or false
# Input: word = "GEEKS";
#        boggle[][]   = {{'G','I','Z'},
#                        {'U','E','K'},
#                        {'Q','S','E'}};
# Output: true
*/

public class Solution {
    public boolean findWord(char[][] board, string word) {
        int m = board.length;
        int n = board[0].length;

        int[][] mov = {
            {-1, 0}, {-1, 1}, {-1, -1},
            {0, 1}, {0, -1},
            {1, 0}, {1, -1}, {1, 1}
        };

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean[][] visited = new boolean[m][n];
                if (dfs(board, i, j, word, 0, visited, mov)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int i, int j, string word, int k, boolean[][] visited, int[][] mov) {
        if (k == word.length()) {
            return true;
        }

        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || board[i][j] != word.chatAt(k)) {
            return false;
        }

        visited[i][j] = true;
        for (int q = 0; q < mov.length; ++q) {
            int x = i + mov[q][0];
            int y = j + mov[q][1];


            if (dfs(board, x, y, word, k + 1, visited, mov)) {
                return true;
            }
        }

        visited[i][j] = false;

        return false;
    }
}
