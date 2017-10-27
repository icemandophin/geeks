/*
add (i > start && nums[i] == nums[i-1]) to capture and
avoid duplicate result added to result
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> cur = new ArrayList<>();
        backtrack(res, cur, nums, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> cur, int [] nums, int start){
        res.add(new ArrayList<>(cur));
        for(int i = start; i < nums.length; ++i) {
            // remove duplicate scenario
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }
            cur.add(nums[i]);
            // no dup value
            backtrack(res, cur, nums, i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}
