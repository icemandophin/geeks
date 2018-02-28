/*
sorting of nums[] takes O(NlogN) time
map element with max consecutive seq len cantains it
notice the approach of updating element and edge elements of its boundary
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        // map each element to longest seq length that includes element
        Map<Integer, Integer> map = new HashMap<>();

        for (int a : nums) {
            // skip if element has been handled
            if (!map.containsKey(a)) {
                // check max len of left and right consecutive neighbors
                // since a is new added => cur left/right len does NOT contains a
                int left = map.containsKey(a - 1) ? map.get(a - 1) : 0;
                int right = map.containsKey(a + 1) ? map.get(a + 1) : 0;
                // cur element connect existing left/right seq => merge len
                int len = right + left + 1;
                res = Math.max(res, len);
                // extend the length of cur element and boundary
                // no need to update len of intermediate elements within range
                // since new add/update only happens on boundary
                map.put(a, len);
                map.put(a - left, len);
                map.put(a + right, len);
            }
        }

        return res;
    }
}
