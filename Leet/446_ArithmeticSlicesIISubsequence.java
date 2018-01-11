/*
dp[j][i] indicates Arithmetic subsequence whose last 2 elements are a[j], a[i] (j < i)
if there exist a[k] that 0 < k < j and a[i] - a[j] = a[j] - a[k]
=> {a[k], a[j], a[i]} forms new Arithmetic sequence in [k, i], plus all dp[j][i] belongs to dp[k][i]
=> dp[k][i] = Sum{dp[j][i] + 1} where k satisfy requirement above
for diff = a[j] - a[i] need to know # of k that a[j] - a[k] == diff => build hashmap for each a[i]

Analysis: O(n^2) time O(n^2) space
*/
class Solution {
    public int numberOfArithmeticSlices(int[] a) {
        int n = a.length;
        // create list of hashmap to record a[i]'s diff value and # of Arithmetic sequence per diff
        // map[i].get(d) indicates # of Arithmetic sequence (with diff d and last element a[i]) between [0, i]
        Map<Long, Integer>[] map = new Map[n];
        int res = 0;

        // for each a[i], check all prev a[j]
        for (int i = 0; i < n; ++i) {
            // init map for a[i]
            map[i] = new HashMap<Long, Integer>(i);
            // check diff
            for (int j = 0; j < i; ++j) {
                // use long for diff to cover out-of-int-scope scenario
                long diff = (long)a[i] - a[j];
                // no valid arithmetic subsequence that can have difference out of the Integer value range
                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) {
                    continue;
                }
                // get # of Arithmetic sequence with diff in [0, j]
                int cnt = map[j].getOrDefault(diff, 0);
                // add dp[0][j] to res
                res += cnt;
                // update map[i]:
                // if map[j] shows there is matches before a[j] that a[j] - a[k] == a[i] - a[j]
                // then dp[0][i] += dp[0][j] + 1
                map[i].put(diff, map[i].getOrDefault(diff, 0) + cnt + 1);
            }
        }

        return res;
    }
}
