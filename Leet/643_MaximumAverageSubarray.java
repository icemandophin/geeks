/*
sliding window approach: O(n) time O(1) space
each time subtract a[i] and add a[i + k] => find max sum
*/
class Solution {
    public double findMaxAverage(int[] num, int k) {
        int n = num.length;
        // get sum of first k
        double sum = 0;
        for (int i = 0; i < k; ++i) {
            sum += num[i];
        }
        // traverse and add new/remove old each time
        double res = sum;
        for (int j = k; j < n; ++j) {
            sum += num[j] - num[j - k];
            res = Math.max(res, sum);
        }

        return res / k;
    }
}

/*
 Cumulative Sum approach: O(n) time O(n) space
 calculate pre-sum s[i] = sum from a[0] to a[i]
 then sum[i, j] = s[j] - a[i-1] => find max sum[i, i+k]
*/
class Solution {
    public double findMaxAverage(int[] a, int k) {
        int n = a.length;
        int[] s = new int[n];
        s[0] = a[0];
        // traverse and get pre-sum
        for (int i = 1; i < n; ++i) {
            s[i] = s[i-1] + a[i];
        }
        // traverse and find max subsrray
        // start from top k subarray
        double res = s[k-1] * 1.0 / k;
        for (int j = k; j < n; ++j) {
            res = Math.max(res, (s[j] - s[j - k]) * 1.0 / k);
        }

        return res;
    }
}
