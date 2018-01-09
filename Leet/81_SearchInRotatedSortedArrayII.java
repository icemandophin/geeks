/*
duplicate element in a[] compared to #33
*/
class Solution {
    public boolean search(int[] a, int x) {
        int top = 0;
        int end = a.length - 1;
        while (top <= end) {
            int mid = top + (end - top) / 2;
            if (a[mid] == x) {
                return true;
            } else if (a[mid] > a[top]) {
                // left half sorted
                // need separate branch for a[mid] == a[top] scenario
                if (a[top] <= x && a[mid] > x) {
                    // target in left
                    end = mid - 1;
                } else {
                    top = mid + 1;
                }
            } else if (a[mid] < a[top]) {
                // right half sorted
                if (a[mid] < x && a[end] >= x) {
                    // search in right
                    top = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                // a[mid] equals to a[top]
                // indicates a[top] - a[mid] shares same value => increase top and try again
                // this cause O(n) worst time
                top++;
            }
        }

        // not found
        return false;
    }
}
