/*
pre-order traverse of denary tree
*/
class Solution {
    public int findKthNumber(int n, int k) {
        int cur = 1;

        while (k > 1) {
            long top = cur;
            long end = cur + 1;
            long cnt = 0;
            // count # of nodes between top and end
            // including nodes in sublayer
            while (top <= n) {
                cnt += Math.min(end, n + 1) - top;
                // move to next layer
                top *= 10;
                end *= 10;
            }

            if (k > cnt) {
                // can count next (top, end) pair
                // and minus k for cnts between
                k -= cnt;
                cur++;
            } else {
                k--;
                // move cur to next pre-order node in next layer
                cur *= 10;
            }
        }

        return (int) cur;
    }
}
