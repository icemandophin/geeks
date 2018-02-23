class Solution {
    public int uniquePathsWithObstacles(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        if (a[0][0] == 1) {
            a[0][0] = 0;
        } else {
            a[0][0] = 1;
        }

        // if there is obstacle in 1st row/col
        // cannot reach and set to 0
        for (int i = 1; i < m; ++i) {
            if (a[i - 1][0] != 0 && a[i][0] == 0) {
                a[i][0] = 1;
            } else {
                a[i][0] = 0;
            }
        }
        for (int i = 1; i < n; ++i) {
            if (a[0][i - 1] != 0 && a[0][i] == 0) {
                a[0][i] = 1;
            } else {
                a[0][i] = 0;
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (a[i][j] == 1) {
                    a[i][j] = 0;
                } else {
                    a[i][j] = a[i - 1][j] + a[i][j - 1];
                }
            }
        }

        return a[m - 1][n - 1];
    }
}
