/*
DP thinking: for each vertical cell, water can hold depends on left and right high boundary (smaller one):
fill[i] =  Min{LeftMax[i], RightMax[i]} - a[i]

1. iterate L->R, calculate LeftMax[i]
2. iterate R->L, calculate RightMax[i]
3. iterate L->R, calculate water for each a[i]

Case check:
if a[i] > dp[i] then water[i] = 0
for a[0] and a[N-1], left/right max is 0

Further improvment: merge 2 and 3: update dp[i] and calculate final result at the same time
*/

class Solution {
    public int trap(int[] height) {
        int res = 0;
        int peak = 0;
        int N = height.length;
        int[] dp = new int[N];
        int i;
        // get top height on left side
        for (i=0; i<N; ++i)
        {
            dp[i] = peak;
            peak = Math.max(peak, height[i]);
        }

        peak = 0;

        for (i=N-1; i>=0; --i)
        {
            // find smaller one in leftMax and rightMax
            dp[i] = Math.min(dp[i], peak);
            // update right Max for next cell
            peak = Math.max(peak, height[i]);

            if (dp[i] > height[i])
            {
                res += dp[i] - height[i];
            }
        }

        return res;
    }
}
