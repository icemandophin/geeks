/*
backtrack + DFS
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();

        dfs(root, sum, res, cur);

        return res;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> cur) {
        if (root == null) {
            return;
        }
        // complete condition:
        // (1) got leaf node
        // (2) with leaf node the target is met
        cur.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new ArrayList<Integer>(cur));
        } else {
            // check left/right child approach
            dfs(root.left, sum - root.val, res, cur);
            dfs(root.right, sum - root.val, res, cur);
        }
        // backtrack: remove root.val
        cur.remove(cur.size() - 1);
    }
}
