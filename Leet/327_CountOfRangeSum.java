class Solution {
    public int countRangeSum(int[] a, int low, int high) {
        int n = a.length;
        long[] sum = new long[n + 1];
        int[] res = new int[1];

        sum[0] = 0;
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + a[i];
        }

        mergeSort(sum, low, high, 0, n, res);

        return res[0];
    }

    private long[] mergeSort(long[] a, int low, int high, int x, int y, int[] res) {
        if (x > y) {
            return new long[0];
        } else if (x == y) {
            return new long[] { a[x] };
        } else {
            int m = x + (y - x) / 2;
            // sort left/right subarray
            long[] left = mergeSort(a, low, high, x, m, res);
            long[] right = mergeSort(a, low, high, m + 1, y, res);
            // fill sorted a[x : y] array
            long[] sorted = new long[y - x + 1];
            int l = m - x + 1;
            int r = y - m;

            // for each i in left[], find start/end index j, k that make sum in scope
            for (int i = 0, j = 0, k = 0; i < l; ++i) {
                while (j < r && right[j] - left[i] < low) {
                    j++;
                }

                while (k < r && right[k] - left[i] <= high) {
                    k++;
                }
                // k - j is cnt of subsrray for cur start index i
                // update global cnt
                res[0] += k - j;
            }
            // merge left[] and right[] into sorted[]
            for (int i = 0, j = 0, k = 0; k < y - x + 1; ++k) {
                if (i == l || (j < r && left[i] > right[j])) {
                    sorted[k] = right[j++];
                } else {
                    sorted[k] = left[i++];
                }
            }

            return sorted;
        }

    }
}
