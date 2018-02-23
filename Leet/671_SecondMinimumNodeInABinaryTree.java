class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        } else if (root.left == null) {
            return -1;
        } else {
            int left = root.left.val;
            int right = root.right.val;

            if (root.val == left) {
                left = findSecondMinimumValue(root.left);
            }

            if (root.val == right) {
                right = findSecondMinimumValue(root.right);
            }

            if (left == -1) {
                return right;
            } else if (right == -1) {
                return left;
            } else {
                return Math.min(left, right);
            }
        }
    }
}
