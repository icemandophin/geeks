/*
let P refers to subset of a[i] that with positive symbol,
and N refers to subset with negative symbol
=> sum(P) - sum(N) = target
=> sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
=> 2 * sum(P) = target + sum(a[i]) notice target + sum(a[i]) must be even (quick check)!
=> find # of subset P that sum(P) = (target + sum(a[i])) / 2
=> resolve (0-1 backpack) subproblem with DP, similar to #416
*/
class Solution {
    public int findTargetSumWays(int[] nums, int x) {
        if (nums == null) {
            return 0;
        }
        // get sum of a[i]
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        // quick check for possibility
        if (sum < x || (sum + x) % 2 != 0) {
            // no sum(P) available
            return 0;
        } else {
            return subSum(nums, (sum + x) / 2);
        }
    }
    // find number of ways to get target x with a[]
    public int subSum(int[] a, int x) {
        // dp refers to # of subsets in a[] that sum to x
        int[] dp = new int[x + 1];
        // use nothing to make sum of 0
        dp[0] = 1;
        // notice a[i] must be outer loop:
        // try to make dp[x] with less a[i], then expand source elements
        // by this sequence we init dp[a[i]] = 1 during the process, then build higher result
        for (int cur : a) {
            for (int i = x; i >= cur; --i) {
                // if dp[x - a[i]] can be made, then it is part of dp[x] solution
                dp[i] += dp[i - cur];
            }
        }

        return dp[x];
    }
}
