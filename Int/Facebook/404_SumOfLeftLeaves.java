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
 iterative approach: with stack
 stack stores subtree root which might have left leaf
 remember to check cur node's left/right child is NOT null
 */
 class Solution {
     public int sumOfLeftLeaves(TreeNode root) {
         if (root == null) {
             return 0;
         }
         int res = 0;
         Stack<TreeNode> stk = new Stack<>();
         // start with root
         stk.push(root);
         while (!stk.empty()) {
             // get left child of cur nodef
             TreeNode cur = stk.pop();
             if (cur.left != null) {
                 TreeNode left = cur.left;
                 if (left.left == null && left.right == null) {
                     // find left
                     res += left.val;
                 } else {
                     // push left subtree to stack
                     stk.push(left);
                 }
             }
             // also check right tree
             if (cur.right != null) {
                 TreeNode right = cur.right;
                 // also push subtree to stack
                 if (right.left != null || right.right != null) {
                     stk.push(right);
                 }
             }
         }
         return res;
     }
 }
 
/*
recursive approach:
check left leaf and recur left/right subtree
*/
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.left != null) {
            // check leaf node on left child
            TreeNode left = root.left;
            if (left.left == null && left.right == null) {
                res += left.val;
            } else {
                // recur check left subtree
                res += sumOfLeftLeaves(left);
            }
        }
        // recur right subtree
        res += sumOfLeftLeaves(root.right);

        return res;
    }
}
