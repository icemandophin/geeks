/*
let dp[i][j] be max value can get from a[i : j]
consider k between [i : j] as last balloon to burst:
first burst [i][k - 1] and [k + 1][j], then k connects with i - 1 and j + 1
=> dp[i][j] = max(dp[i][j], nums[i - 1] * nums[k] * nums[j + 1] + dp[i][k - 1] + dp[k + 1][j])
*/

// memorization search:
class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // add boundary element at top & end
        int[][] dp = new int[n + 2][n + 2];
        int[] a = new int[n + 2];

        a[0] = a[n + 1] = 1;
        for (int i = 1; i < n + 1; ++i) {
            a[i] = nums[i - 1];
        }

        return search(a, dp, 1, n);
    }
    // get max value for a[i : j]
    private int search(int[] a, int[][] dp, int x, int y) {
        if (dp[x][y] > 0) {
            return dp[x][y];
        }

        for (int i = x; i <= y; ++i) {
            // find global max by trying each balloon as last to burst
            dp[x][y] = Math.max(dp[x][y],
                                search(a, dp, x, i - 1) + search(a, dp, i + 1, y) + a[x - 1] * a[i] * a[y + 1]);
        }

        return dp[x][y];
    }
}

// dp version:
class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // add extra element on top / end
        int len = n + 2;

        int[][] dp = new int[len][len];
        int[] a = new int[len];
        a[0] = a[n + 1] = 1;
        for (int i = 1; i < n + 1; ++i) {
            a[i] = nums[i - 1];
        }

        // distance between i and j
        for (int d = 2; d < len; ++d) {
            for (int i = 0; i < len - d; ++i) {
                int j = i + d;

                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = Math.max(dp[i][j],
                                       dp[i][k] + dp[k][j] + a[i] * a[k] * a[j]);
                }
            }
        }

        return dp[0][n + 1];
    }
}
