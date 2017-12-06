/*
Notice no dup element in a[]
adjusted binary search:
if a[mid] > a[top] => left half is sorted => check if x in [top, mid] => decide keep left/right half
else right half is sorted => check if x in [mid, end] => decide keep left/right half
*/
class Solution {
    public int search(int[] a, int x) {
        int top = 0;
        int end = a.length - 1;
        while (top <= end) {
            int mid = top + (end - top) / 2;
            if (a[mid] == x) {
                return mid;
            } else if (a[mid] >= a[top]) {
                // left half sorted
                if (a[top] <= x && a[mid] > x) {
                    // target in left
                    end = mid - 1;
                } else {
                    top = mid + 1;
                }
            } else {
                // right half sorted
                if (a[mid] < x && a[end] >= x) {
                    // search in right
                    top = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        // not found
        return -1;
    }
}
