/*
dp[i] is length of longest increasing subsequence that end wtih a[i]
(sequence contains a[i])
when a[i] > a[j]:
if dp[i] < dp[j] + 1 => diff > 1 => dp[i] = dp[j] + 1
else dp[i] = Max{dp[j]}

cnt[i] is number of longest increasing subsequence that end wtih a[i]
when dp[i] increase from dp[j]
if diff > 1 => cnt[i] = cnt[j]
if diff == 1 => cnt[i] += cnt[j]
(add a[i] makes new seq with len cnt[j] + 1)
*/
class Solution {
    public int findNumberOfLIS(int[] a) {
        int n = a.length;
        // max length of increasing seq with last element a[i]
        int[] dp = new int[n];
        // cnt num of longest increasing seq with last element a[i]
        int[] cnt = new int[n];
        // record max len of increasing seq
        int max = 1;
        // record count of longest increasing seq
        int res = 0;
        // traverse for dp[] and cnt[]
        for (int i = 0; i < n; ++i) {
            // init len is 1 (a[i])
            dp[i] = 1;
            cnt[i] = 1;
            // check all prev elements
            for (int j = 0; j < i; ++j) {
                if (a[i] > a[j]) {
                    if (dp[i] < dp[j] + 1) {
                        // new_len - cur_len >= 2
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[i] == dp[j] + 1) {
                        // new_len + a[i] == cur_len
                        // add to cnt
                        cnt[i] += cnt[j];
                    }
                }
            }
            // update max len so far and its count
            if (dp[i] > max) {
                max = dp[i];
                res = cnt[i];
            } else if (dp[i] == max) {
                // same len => add cnt
                res += cnt[i];
            }
        }

        return res;
    }
}
