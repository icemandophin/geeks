/*
iterative DP approach:
dp[target] = sum(dp[target - nums[i]])
where 0 <= i < nums.length, and target >= nums[i]
*/
class Solution {
    public int combinationSum4(int[] nums, int target) {
        // store combination results for target [0 : target]
        int[] dp = new int[target + 1];
        // when target = 0, only one way to get zero, which is using 0
        // hence dp[0] = 1
        dp[0] = 1;
        // traverse from 1 to target
        // for each element, traverse source array and add up all sub dp value
        for (int i = 1; i <= target; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                // check if cur nums[j] can be sub dp
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}

/*
top-down memory search version:
*/
class Solution {
    // store combination results for target [0 : target]
    private int[] dp;
    public int combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        // mark uncalculated dp with -1
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            // directly return prev result
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            // check if dp[i] can be sub-problem
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        // save result to dp[]
        dp[target] = res;
        return res;
    }
}

/*
recursive approach:
*/
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += combinationSum4(nums, target - nums[i]);
            }
        }
        return res;
    }
}
