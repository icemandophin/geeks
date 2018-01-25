/*
DP approach:
1. sort envelops
2. for j > i if e[j] can cover e[i]
=> dp[j] = Max{dp[i]} + 1
*/
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // sort enevlope by width and length
        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            }
        });

        int n = envelopes.length;
        // dp[i] is max doll size that sorted ith envelop can hold
        int[] dp = new int[n];
        int res = 0;

        for (int j = 0; j < n; ++j) {
            // init dp for single envelop
            dp[j] = 1;
            for (int i = 0; i < j; ++i) {
                if (envelopes[j][0] > envelopes[i][0] && envelopes[j][1] > envelopes[i][1]) {
                    // update max doll number
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                }
            }
            // update max so far for dp[j]
            res = Math.max(dp[j], res);
        }

        return res;
    }
}
