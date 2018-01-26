/*
cut rectangle into 4 pieces:
1 2

3 4

=> area/sum[0 : 4] = sum[0 : 2] + sum[0 : 3] - sum[0 : 1] + a[4]
=> pre-process then requested rectangle sum can be calculated in O(1)
*/
class NumMatrix {
    // sum[i][j] records sum from (0, 0) to (i, j)
    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        // pre-process matrix sum and save to sum[][]
        int m = matrix.length;
        int n = matrix[0].length;
        sum = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }
    // get rectangle sum from sum[][]
    // area/sum[1 : 4] = sum[0 : 4] - sum[0 : 3] - sum[0 : 2] + sum[0 : 1]
    public int sumRegion(int x1, int y1, int x2, int y2) {
        return sum[x2 + 1][y2 + 1] - sum[x2 + 1][y1] - sum[x1][y2 + 1] + sum[x1][y1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
