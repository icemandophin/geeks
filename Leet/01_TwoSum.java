/*
one-time traverse: for each a[i]
(1) check if X - a[i] exist in mapping
(2) add {a[i], i} into mapping
*/

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
        int i;

        for(i = 0; i < nums.length; ++i)
        {
            if(hash.containsKey(target - nums[i]))
            {
                res[1] = i;
                res[0] = hash.get(target - nums[i]);
                break;
            }
            hash.put(nums[i], i);
        }
        return res;

    }
}
