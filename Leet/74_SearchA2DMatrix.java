/*
adjusted Binary Search: O(log(M*N))
treat entire matrix as single folding sorted array
coordinate convert: for M*N matrix
array[i] = matrix[i / N][i % N]
*/
class Solution {
    public boolean searchMatrix(int[][] a, int x) {
        if (a == null || a.length == 0) {
            return false;
        }
        int m = a.length;
        int n = a[0].length;
        // set boundary for search
        int top = 0;
        int end = m * n - 1;
        // binary search
        while (top <= end) {
            int mid = top + (end - top) / 2;
            if (a[mid / n][mid % n] == x) {
                return true;
            } else if (a[mid / n][mid % n] < x) {
                top = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }
}

/*
binary search 1st col, then associated row:
*/
class Solution {
    public static boolean searchMatrix(int[][] a, int x) {
        if (a == null || a.length == 0) {
            return false;
        }
        if (a[0] == null || a[0].length == 0) {
            return false;
        }
        int m = a.length;
        int n = a[0].length;
        // search 1st col first
        int top = 0;
        int end = m - 1;
        while (top + 1 < end) {
            int mid = top + (end - top) / 2;
            if (a[mid][0] == x) {
                return true;
            } else if (a[mid][0] < x) {
                top = mid;
            } else {
                end = mid;
            }
        }
        // ensure we take the correct row value that a[row][0] <= x
        int row = end;
        if (a[end][0] <= x) {
            row = end;
        } else if (a[top][0] <= x) {
            row = top;
        } else {
            return false;
        }
        // binary search this row
        top = 0;
        end = n - 1;
        while (top + 1< end) {
            int mid = top + (end - top) / 2;
            if (a[row][mid] == x) {
                return true;
            } else if (a[row][mid] < x) {
                top = mid;
            } else {
                end = mid;
            }
        }
        // check both top and end to find x
        if (a[row][top] == x) {
            return true;
        } else if (a[row][end] == x) {
            return true;
        } else {
        	return false;
        }
    }
}
