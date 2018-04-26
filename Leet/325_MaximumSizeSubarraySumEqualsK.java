public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int res = 0;
        int sum = 0;
        // map of preSum and its first index
        Map<Integer, Integer> map = new HashMap<>();
        // add 0 -> -1 to cover preSum = k scenario
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int val = sum - k;

            if (map.containsKey(val)) {
                // found subsrray => update max len
                res = Math.max(res, i - map.get(val));
            }
            // only record first index to get max length
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return res;
    }
}
