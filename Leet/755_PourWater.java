class Solution {
    public int[] pourWater(int[] h, int v, int k) {
        if (h == null || h.length == 0 || v ==0) {
            return h;
        }
        // record cur position of drop
        int idx;

        while (v > 0) {
            idx = k;
            // try to flow left
            for (int i = k - 1; i >= 0; i--) {
                if (h[i] > h[idx]) {
                    // stop for local low point
                    break;
                } else if (h[i] < h[idx]) {
                    idx = i;
                }
            }
            // confirm move to left
            if (idx != k) {
                h[idx]++;
                v--;
                continue;
            }
            // check
            for (int i = k + 1; i < h.length; ++i) {
                if (h[i] > h[idx]) {
                    break;
                } else if (h[i] < h[idx]) {
                    idx = i;
                }
            }
            // move to right or keep in k
            h[idx]++;
            v--;
        }

        return h;
    }
}
