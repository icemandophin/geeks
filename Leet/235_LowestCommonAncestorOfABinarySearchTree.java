/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 /*
 iterative approach:
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        while (true) {
            if (root.val > Math.max(p.val, q.val)) {
                // p, q both in left child
                root = root.left;
            } else if (root.val < Math.min(p.val, q.val)) {
                // both in right child
                root = root.right;
            }
            else {
                // cur root is LCA
                break;
            }
        }
        return root;
    }
}
/*
recursive approach:
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        // check value of p, q and root
        if (p.val > root.val && q.val > root.val) {
            // recur search on right child
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            // both on left side
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
}
