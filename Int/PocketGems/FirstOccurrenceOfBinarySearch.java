/*
same as leetcode 34
*/

// find first show of target x
// descrese right boundary when mid == x
// when exit loop top contains the target index
private int findLeft(int[] a, int x) {
    int top = 0;
    int end = a.length - 1;

    while (top <= end) {
        int mid = top + (end - top) / 2;

        if (a[mid] < x) {
            top = mid + 1;
        } else {
            // this includes a[mid] == x scenario
            // when end = top + 1 => mid = top
            // top points to 1st index when loop exit
            end = mid - 1;
        }
    }

    if (top < a.length && a[top] == x) {
        return top;
    } else {
        return -1;
    }
}
