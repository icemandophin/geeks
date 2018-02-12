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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        String cur = new String("");
        dfs(root, res, cur);

        return res;
    }

    private void dfs(TreeNode root, List<String> res, String cur) {
        if (root == null) {
            return;
        }
        // check if it is top root
        if (!cur.isEmpty()) {
            cur += "->";
        }
        cur += root.val;
        // add cur to res for leaf node
        if (root.left == null && root.right == null) {
            res.add(cur);
            return;
        }

        dfs(root.left, res, cur);
        dfs(root.right, res, cur);
    }
}
