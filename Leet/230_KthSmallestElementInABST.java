/*
BST => iterative inorder traversal with stack
Time Complexity: O(k) Space Complexity: O(h)
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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;

        while (!s.empty() || p != null) {
            if (p != null) {
                // push left node into stack
                s.push(p);
                p = p.left;
            } else {
                // traverse cur node
                p = s.pop();
                --k;
                if (k == 0) {
                    // found kth smallest one
                    return p.val;
                }
                // move to right node if exist
                p = p.right;
            }
        }
        // not found - less than k elements
        return -1;
    }
}
