/*
apply backtrack model
*/
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        // cur combination of 1 - 9
        List<Integer> cur = new ArrayList<Integer>();
        // start from number 1
        backtrack(k, n, res, cur, 1);
        return res;
    }
    private void backtrack(int k, int target, List<List<Integer>> res, List<Integer> cur, int start) {
        if (cur.size() == k) {
            if (target == 0) {
                // find valid combination, add to res
                res.add(new ArrayList<Integer>(cur));
            }
            // else cur combination fail to match target value
            return;
        }
        for (int i = start; i < 10; ++i) {
            // check if i can be added to build result
            if (i <= target) {
                cur.add(i);
                // resolve sub-problem
                backtrack(k, target - i, res, cur, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
