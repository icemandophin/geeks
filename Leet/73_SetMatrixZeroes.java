/*
use 1st row and col as flag array, traverse twice:
1. for each a[i][j] == 0 => set a[i][0] and a[0][j] = 0
2. for each a[i][j] if a[i][0] || a[0][j] == 0 => set a[i][j] = 0

Notice first row / col needed to be handled in separate
*/
class Solution {
    public void setZeroes(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        boolean row = false;
        boolean col = false;

        // record if exist 0 in 1st row / col
        for (int i = 0; i < m; i++) {
            if (a[i][0] == 0) {
                col = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (a[0][j] == 0) {
                row = true;
            }
        }
        // set 1st row / col for "internal" elements
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i][j] == 0) {
                    a[i][0] = 0;
                    a[0][j] = 0;
                }
            }
        }
        // set "internal" elements to 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i][0] == 0 || a[0][j] == 0) {
                    a[i][j] = 0;
                }
            }
        }
        // set 1st row / col
        if (col) {
            for (int i = 0; i < m; i++) {
                a[i][0] = 0;
            }
        }
        if (row) {
            for (int j = 0; j < n; j++) {
                a[0][j] = 0;
            }
        }
    }
}
