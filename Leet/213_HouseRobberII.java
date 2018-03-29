/*
cannot rob first and last at the same time
=> try rob [0, N - 2] and [1, N - 1] => get larger
*/
class Solution {
    public int rob(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length;
        if (n == 1) {
            return a[0];
        }

        return Math.max(rob(a, 0, n - 2), rob(a, 1, n - 1));
    }
    // get max rob value in [top, end]
    private int rob(int[] a, int top, int end) {
        int pre = 0;
        int cur = 0;

        for (int i = top; i <= end; ++i) {
            // cur contains max value of [top, i - 1]
            // pre contains max value of [top, i - 2]
            int max = Math.max(pre + a[i], cur);
            pre = cur;
            cur = max;
        }

        return cur;
    }
}
