/*
subarray sum[i : j] => build preSum[0 : i]
hash{preSum, # of this sum} for O(1) check
*/
class Solution {
    public int subarraySum(int[] a, int k) {
        int cur = 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // add 0 -> 1 for preSum = k scenario
        map.put(0, 1);

        for (int i = 0; i < a.length; ++i) {
            // calculate preSum[i]
            cur += a[i];
            if (map.containsKey(cur - k)) {
                // there are preSum[j] in hash that cur/preSum[i] - preSum[j] = k
                res += map.get(cur - k);
            }
            // save/update the preSum value of this iteration
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        return res;
    }
}
