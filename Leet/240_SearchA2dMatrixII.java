/*
for left corner, every element above is smaller, every element on right side is larger
sub-matrix follows the same pattern => start from left corner and move during search
*/
class Solution {
    public boolean searchMatrix(int[][] a, int target) {
        if (a == null || a.length == 0) {
            return false;
        }
        if (a[0] == null || a[0].length == 0) {
            return false;
        }
        int m = a.length;
        int n = a[0].length;
        // start from left corner
        int x = m - 1;
        int y = 0;
        while (x >= 0 && y < n) {
            if (a[x][y] == target) {
                return true;
            } else if (a[x][y] < target) {
                // go right and search bigger
                ++y;
            } else {
                // go up and search smaller
                --x;
            }
        }
        // not found
        return false;
    }
}
