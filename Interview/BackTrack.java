class Solution {
    public List<List<Integer>> combinationSum(int[] source, int target) {
        // contains final result
        List<List<Integer>> res = new ArrayList<>();
        // store cur combination
        List<Integer> cur = new ArrayList<>();
        // backtrack sub-problem
        backtrack(source, target, res, cur, 0);

        return res;
    }
    // util method to backtrack all sub-combination scenarios
    private void backtrack(int[] source, int target, List<List<Integer>> res, List<Integer> cur, int start) {
        if (target is met) {
            if (restriction also met) {
                // add cur combination to res
                res.add(new ArrayList<>(cur));
            }
            else {
                // give up cur combination
            }
            return;
        }
        // build valid combination with source[start : end of source]
        for (int i = start; i < source.length; ++i) {
            // check if source[i] can be part of a valid combination
            if (source[i] can be part of target) {
                // add below to remove duplicate scenario
                if (i > start && source[i] == source[i-1]) {
                    continue;
                }
                // add source[i] to cur combination
                cur.add(source[i]);
                // continue to resolve sub-problem of target - source[i]
                // continue with i when it is possible to reuse elements
                // otherwise use i + 1 to remove duplicate
                backtrack(source, target - source[i], res, cur, i);
                // remove source[i] and backtrack
                cur.remove(cur.size() - 1);
            }
        }
    }
}
