/*
DP: O(n) time, O(n) space
dp[i] represent # of Arithmetic subarray in [0, i]
length >= 3 => start from a[2]
if {a[i-1], a[i], a[i+1]} makes new Arithmetic subarray => dp[i+1] += dp[i] + 1
else dp[i+1] = 0;
*/
class Solution {
    public int numberOfArithmeticSlices(int[] a) {
        if (a == null || a.length < 3) {
            return 0;
        }
        int res = 0;
        int n = a.length;
        int[] dp = new int[n];
        for (int i = 2; i < n; ++i) {
            if ((a[i] - a[i - 1]) == (a[i - 1] - a[i - 2])) {
                // makes new Arithmetic subarray
                // subarray => only need to consider sequent elements
                dp[i] = dp[i - 1] + 1;
            }
            // add to result
            res += dp[i];
        }

        return res;
    }
}

/*
optimize space to O(1):
dp[i] only depend on dp[i-1] => rolling array
Notice: manually clean dp[0]/dp[1] when condition not met
*/
class Solution {
    public int numberOfArithmeticSlices(int[] a) {
        if (a == null || a.length < 3) {
            return 0;
        }
        int res = 0;
        int n = a.length;
        int[] dp = new int[2];
        for (int i = 2; i < n; ++i) {
            if ((a[i] - a[i - 1]) == (a[i - 1] - a[i - 2])) {
                // makes new Arithmetic subarray
                // subarray => only need to consider sequent elements
                dp[i % 2] = dp[(i - 1) % 2] + 1;
            }
            else {
                // since dp[0], dp[1] got reused
                // need to reset when Arithmetic subarray not met
                dp[i % 2] = 0;
            }
            // add to result
            res += dp[i % 2];
        }

        return res;
    }
}
