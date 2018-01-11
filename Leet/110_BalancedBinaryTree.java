/*
recursive approach calculate depth of subtree repeatedly
=> when calculate depth of cur root, if ldft/right child is not balanced
directly return -1 from bottom to top
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
public class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // get subtree height
        int left = height(root.left);
        int right = height(root.right);
        // directly return -1 to indicate left/right subtree not balanced
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            // return true legnth of cur tree
            return Math.max(left, right) + 1;
        }
    }
}
