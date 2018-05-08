/*
for binary tree
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) {
            return null;
        } else if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}

/*
for BST - by value
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
