/*
dp[n] indicates number of unique BST for [1 : n]:
1. select 1 element as root
2. root i partition remaining n-1 elements to
left: [1 : i-1] => len is (i-1)-1+1 = i
right: [i+1 : n] => len is n-i
=> number of unique left subtree: dp[i] right subtree dp[n-1-i]
=> number of unique BST with i as root: dp[i] * dp[n-1-i]
3. total number of unique BST [1 : n] is sum of counts with each element as root
*/
class Solution {
    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        }
        // dp[n] is number of unique BST for [1 : n]
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // calculate dp for [0 : i]
        for (int i = 2; i <= n; ++i) {
            // select j as root
            for (int j = 1; j <= i; ++j) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }
}
