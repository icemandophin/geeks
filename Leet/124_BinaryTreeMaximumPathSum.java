/*
max sum path can start/end in any node => keep global res to update
path between 2 nodes always has common root => recur check max sum path for each subtree
cal vertical sum => helper func always return max path sum that connects root and one of its sub-node
*/
class Solution {
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // record global max path sum during recursion
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;

        helper(root, res);

        return res[0];
    }
    // return max "vertical" path sum which goes through root-left or root-right
    // also update global max path sum in res
    private int helper(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        // calculate max path sum going through left/right child
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        // only add child path sum if it is > 0
        // when child path sum < 0 => abandon and start path from root
        left = Math.max(left, 0);
        right = Math.max(right, 0);
        // larger of left/right child sum + root value makes max root-to-leaf sum
        int max = Math.max(left, right) + root.val;
        // update global res with max path sum generated from cur tree
        res[0] = Math.max(res[0], left + right + root.val);

        return max;
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
