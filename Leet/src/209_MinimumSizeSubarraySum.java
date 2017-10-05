/*
catepillar approach with 2 pointers:
increase front j until subarray sum no less than s
then shrink back i and update min length until subarray sum < s again
*/
class Solution {
    public int minSubArrayLen(int s, int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        // define pointers
        int i = 0;
        int j = 0;
        // save current sum
        int sum = 0;
        for (j = 0; j < a.length; ++j) {
            sum += a[j];
            while (sum >= s) {
                res = Math.min(res, j - i + 1);
                sum -= a[i++];
            }
        }
        // check if s is ever reached before return res
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
