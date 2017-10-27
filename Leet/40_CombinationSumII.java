/*
same as 39. Combination Sum, but cannot reuse same element
=> change backtrack start to i + 1
*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] source, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // store cur combination
        List<Integer> cur = new ArrayList<>();
        // pre-process the input with sorting
        Arrays.sort(source);
        // backtrack sub-problem
        backtrack(source, target, res, cur, 0);

        return res;
    }
    // util method to backtrack all sub-scenarios
    private void backtrack(int[] source, int target, List<List<Integer>> res, List<Integer> cur, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i < source.length; ++i) {
            // it is possible that source[i] == source[i - 1], which leads to dup result
            // hence skip this scenario
            if (i > start && source[i] == source[i - 1]) {
                continue;
            }
            // same as Combination Sum
            if (source[i] <= target) {
                cur.add(source[i]);
                // start from i + 1 => avoid reuse of same element
                backtrack(source, target - source[i], res, cur, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
