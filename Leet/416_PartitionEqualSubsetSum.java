/*
similar to 0-1 backpack problem
dp[x] is built from each a[i]:
if there exist i that dp[x - a[i]] is true => dp[x] is true
*/
class Solution {
    public boolean canPartition(int[] a) {
        if (a == null || a.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        // sum of a[] must be even to be divided
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        // dp[i] is true if target i can be formed by a[]
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int cur : a) {
            for (int j = sum; j >= cur; --j) {
                // value x is accessible if any of x - a[i] is accessible
                dp[j] |= dp[j - cur];
            }
        }

        return dp[sum];
    }
}
