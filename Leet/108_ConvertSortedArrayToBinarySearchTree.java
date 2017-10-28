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
    public TreeNode sortedArrayToBST(int[] nums) {
        return recurBuild(nums, 0, nums.length - 1);
    }
    TreeNode recurBuild(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recurBuild(nums, start, mid - 1);
        root.right = recurBuild(nums, mid + 1, end);
        return root;
    }
}
