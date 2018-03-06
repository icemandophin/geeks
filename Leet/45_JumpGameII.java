/*
different greedy from #55:
cur = Max{i + a[i]} where 0 <= i <= cover
*/
class Solution {
    public int jump(int[] a) {
        int res = 0;
        // record max distance can cover so far
        int cover = 0;
        // record max distance can be reached by res + 1 steps
        int cur = 0;

        for (int i = 0; i < a.length; ++i) {
            if (i > cover) {
                // cur position exceeds prev max distance
                // need one additional jump to reach
                // => update max distance and cnt
                cover = cur;
                ++res;
            }

            cur = Math.max(cur, i + a[i]);
        }

        return res;
    }
}



/*
DP approach (TLE):
dp[i] records min steps of jump from 0 to i
min step to jump to i comes from min step jump to j then j -> i
if accessible from j to i
=> dp[i] = Min{dp[j] + 1} where  0 <= j < i
and a[j] + j >= i
*/
public class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] != Integer.MAX_VALUE && j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len - 1];
    }
}
