class Solution {
    public List<List<Integer>> combinationSum(int[] source, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // store cur combination
        List<Integer> cur = new ArrayList<>();
        // backtrack sub-problem
        backtrack(source, target, res, cur, 0);

        return res;
    }
    // util method to backtrack all sub-scenarios
    private void backtrack(int[] source, int target, List<List<Integer>> res, List<Integer> cur, int start) {
        if (target == 0) {
            // target met, add cur combination to res
            res.add(new ArrayList<>(cur));
            return;
        }
        // build valid combination with source[start : N - 1]
        for (int i = start; i < source.length; ++i) {
            // check if cur element can be part of solution
            if (source[i] <= target) {
                // build valid combination that contains element a[i]
                cur.add(source[i]);
                // not i + 1 because it is possible to reuse same elements
                backtrack(source, target - source[i], res, cur, i);
                // remove element just added
                cur.remove(cur.size() - 1);
            }
        }
    }
}
