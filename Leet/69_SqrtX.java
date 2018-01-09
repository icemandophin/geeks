/*
Pay attention to int overflow => no mid*mid but x/mid
(x/2 + 1)(x/2 + 1) >= x, hence right boundary can be x/2 + 1
*/
/*
binary search approach:
for x>=0, (x/2 + 1)(x/2 + 1) >= x
its sqrt will not be bigger than x/2+1
hence right boundary can be x/2 + 1
Notice: if x does not exist in sorted A[0, N-1] array
binary search will end by top > end => A[end] < x < A[top]
Avoid overflow: use mid == x/mid as condition
*/
class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int top = 1;
        int end = x / 2 + 1;
        int mid;
        int cur;
        while (top <= end) {
            mid = top + (end - top) / 2;
            // mid*mid can easily overflow, hence compare mid and x/mid
            if (mid == x/mid) {
                return mid;
            }
            else if (mid < x/mid) {
                top = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return end;
    }
}
