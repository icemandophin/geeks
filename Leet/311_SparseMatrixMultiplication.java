/*
sparse matrix => multiply only when a[i][k] and b[k][j] are not 0
*/
class Solution {
    public int[][] multiply(int[][] a, int[][] b) {
        // col_A should be equal to row_B
        int ra = a.length;
        int ca = a[0].length;
        int cb = b[0].length;
        int[][] res = new int[ra][cb];

        for (int i = 0; i < ra; ++i) {
            for (int k = 0; k < ca; ++k) {
                if (a[i][k] != 0) {
                    for (int j = 0; j < cb; ++j) {
                        if (b[k][j] != 0) {
                            // res[i][j] = Sum {a[i][k] * b[k][j]}
                            res[i][j] += a[i][k] * b[k][j];
                        }
                    }
                }
            }
        }

        return res;
    }
}