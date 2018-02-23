/*
two direction => two types of coordinate diff
Notice: bounce on left/top and right/buttom are different   
*/
class Solution {
    public int[] findDiagonalOrder(int[][] a) {
        if (a.length == 0 || a[0].length == 0) {
            return new int[0];
        }

        int m = a.length;
        int n = a[0].length;
        int[] res = new int[m * n];
        int[][] move = {{-1, 1}, {1, -1}};
        // record cur row, col
        int r = 0;
        int c = 0;
        // flag cur direction
        // 0: toward NE  1: toward SW
        int d = 0;

        for (int i = 0; i < m * n; ++i) {
            res[i] = a[r][c];
            r += move[d][0];
            c += move[d][1];

            if (r >= m) {
                // hit last row (one step further)
                // row back to last
                // col back and add one
                r = m - 1;
                c += 2;
                d = 1 - d;
            } else if (c >= n) {
                // hit last col
                r += 2;
                c = n - 1;
                d = 1 - d;
            } else if (r < 0) {
                // hit top row
                r = 0;
                d = 1 - d;
            } else if (c < 0) {
                // hit first col
                c = 0;
                d = 1 - d;
            }
        }

        return res;
    }
}
