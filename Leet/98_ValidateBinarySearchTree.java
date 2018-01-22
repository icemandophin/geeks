/*
BST => check if inorder traversal values are ascending
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
        // always compare current node value with prev node value
        return inorder(root, new TreeNode[1]);
    }
    // check if inorder traversal is ascending
    private boolean inorder(TreeNode root, TreeNode[] pre) {
        if (root == null) {
            return true;
        }
        // check left subtree and cur root
        if (!inorder(root.left, pre) || (pre[0] != null && pre[0].val >= root.val)) {
            return false;
        }
        // update prev node
        pre[0] = root;
        // check right subtree

        return inorder(root.right, pre);
    }
}

/*
recursive:
1. left.val < root.val < right.val
2. check left/right subtree
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
        return checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    // recur helper
    private boolean checkBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        // check root
        if (root.val <= min || root.val >= max) {
            return false;
        }
        // check subtrees
        return checkBST(root.left, min, root.val) && checkBST(root.right, root.val, max);
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
