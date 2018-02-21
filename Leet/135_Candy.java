class Solution {
    public int candy(int[] a) {
        int n = a.length;
        int[] cnt = new int[n];

        for (int i = 0; i < n; ++i) {
            cnt[i] = 1;
        }
        // ensure rule from left side
        for (int i = 0; i < n - 1; ++i) {
            if (a[i + 1] > a[i]) {
                cnt[i + 1] = cnt[i] + 1;
            }
        }
        // ensure rule from right side
        for (int i = n - 1; i > 0; --i) {
            if (a[i - 1] > a[i]) {
                // keep prev order by select max
                cnt[i - 1] = Math.max(cnt[i - 1], cnt[i] + 1);
            }
        }

        int res = 0;
        for (int i : cnt) {
            res += i;
        }

        return res;
    }
}
