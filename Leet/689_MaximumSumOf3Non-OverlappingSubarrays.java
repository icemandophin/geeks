/*
consider start index of the middle interval: i then k <= i <= n-2k
left[i] stores the start index for the first interval in range [0, i]
right[i] stores the start index for the third interval in range [i, n - 1]

preprocess sum[i] to get left[i]/right[i] in O(N)
lexicographical smallest order interval => always select the leftmost one
*/
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] a, int k) {
        int n = a.length;
        int[] sum = new int[n + 1];
        // left[i] stores start index of max left k sum
        // when start index of middle is i
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[3];
        // build preSum
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + a[i];
        }
        // DP max sum of left interval (left->right)
        int max = sum[k] - sum[0];
        for (int i = k; i <= n - 2 * k; ++i) {
            // left interval [0 : k] - [i - k + 1 : i - 1]
            // check if [i - k, i] has larger sum
            if (sum[i + 1] - sum[i - k + 1] > max) {
                max = sum[i + 1] - sum[i - k + 1];
                left[i] = i - k + 1;
            } else {
                left[i] = left[i - 1];
            }
        }
        // DP max sum of left interval (left->right)
        max = sum[n] - sum[n - k];
        right[n - k] = n - k;
        for (int i = n - 1 - k; i >= 2 * k; --i) {
            if (sum[i + k] - sum[i] >= max) {
                max = sum[i + k] - sum[i];
                right[i] = i;
            } else {
                right[i] = right[i + 1];
            }
        }
        // find index i that makes max globla sum
        max = 0;
        for (int i = k; i <= n - 2 * k; ++i) {
            int l = left[i - 1];
            int r = right[i + k];
            int cur = sum[l + k] - sum[l] + sum[r + k] - sum[r] + sum[i + k] - sum[i];
            if (cur > max) {
                res[0] = l;
                res[1] = i;
                res[2] = r;
                max = cur;
            }
        }

        return res;
    }
}
