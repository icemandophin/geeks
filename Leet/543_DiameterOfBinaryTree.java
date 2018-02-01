public class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = new int[1];
        postorder(root, max);

        return max[0];
    }

    private int postorder(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }

        int left = postorder(root.left, max);
        int right = postorder(root.right, max);
        max[0] = Math.max(max[0], left + right);

        return Math.max(left, right) + 1;
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
