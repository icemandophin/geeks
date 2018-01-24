/*
BST => binary search key:
1. target node is leaf => make it null
2. if one child of target is null => replace with another
3. if both children exist => find and replace with left-most child in right subtree
then recursively delete left-most child in right subtree
(find max in left subtree and recursively delete in left subtree also works)
*/
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // recur search
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // replace with min val in right subtree
                root.val = getMin(root.right);
                // delete right subtree's leftmost node
                root.right = deleteNode(root.right, root.val);
            }
        }

        return root;
    }
    // find min/left-most in the tree
    private int getMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }

        return root.val;
    }
}
