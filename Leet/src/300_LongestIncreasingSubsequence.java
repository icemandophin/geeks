class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int res = 0;
        for (int x : nums) {
            int i = Arrays.binarySearch(dp, 0, res, x);
            if (i < 0) {
                i = -(i + 1);
                dp[i] = x;
                if (i == res) {
                    res++;
                }
            }
        }
        return res;
    }
}
