/*
even simplified from 2 pointers: record cur max value and length
if a[i] <= a[i - 1] => len = 1
*/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        // record cur LCIS length and max LCIS length so far
        int cnt = 0;
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                cnt++;
            } else {
                // reset cnt
                cnt = 1;
            }
            // update res
            res = Math.max(res, cnt);
        }

        return res;
    }
}
