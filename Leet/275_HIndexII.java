/*
sorted by citation times => binary search
n - mid is number of papers whose citation cnt > citations[mid]:
1. n - mid == citations[mid] => res
2. n - mid < citations[mid] => no enough papers with cnt > c[mid] => search in left
3. n - mid > citations[mid] => raise bar of cnt for better soln => search in right
*/
class Solution {
    public int hIndex(int[] c) {
        int n = c.length;
        int top = 0;
        int end = n - 1;

        while (top <= end) {
            int mid = top + (end - top) / 2;
            if (n - mid <= c[mid]) {
                end = mid - 1;
            } else {
                top = mid + 1;
            }
        }

        return n - top;
    }
}
