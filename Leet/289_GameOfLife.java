/*
in place approach:
1. check:
alive: 1 + 2 = 3
reproduce: 0 + 2 = 2;
dead: 0
dying: 1
2. update: b[i][j] / 2
alive/reproduce => 1
dead/dying => 0
*/
class Solution {
    public void gameOfLife(int[][] b) {
        if (b.length == 0 || b[0].length == 0) {
            return;
        }

        int m = b.length;
        int n = b[0].length;

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // count # of neighbor lives
                int cnt = 0;
                for (int k = 0; k < 8; ++k) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        cnt += b[x][y] & 1;
                    }
                }
                // for cell that keep alive 1 -> 3
                // for cell that reproduce 0 -> 2
                // so that b[i][j] /2 = 1
                if ((b[i][j] == 0 && cnt == 3) || (b[i][j] == 1 && (cnt == 2 || cnt == 3))) {
                    b[i][j] += 2;
                }
            }
        }
        // for cell that dead or over-population => (1/0) / 2 = 0
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                b[i][j] >>= 1;
            }
        }
    }
}
