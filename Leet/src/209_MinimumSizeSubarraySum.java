class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if((nums == null) || (nums.length == 0))
        {
            return 0;
        }

        int j = 0, i = 0;
        int res = 0;
        int min = Integer.MAX_VALUE;

        while (j < nums.length)
        {
            res += nums[j++];

            while (res >= s)
            {
                min = Math.min(min, j-i);
                res -= nums[i++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
