/*
iterative approach: time O(logn); space O(1)
*/
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null) {
            if (root.val <= p.val) {
                // successor must be in right subtree
                root = root.right;
            } else {
                // successor can be root itself
                // or another node in left subtree
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }
}

/*
recursive approach: time O(logn); space O(logn) for stack
search in subtree according to root and target value
*/
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return root;
        }

        if (root.val <= p.val) {
            // successor in the right subtree
            TreeNode right = inorderSuccessor(root.right, p);
            return right;
        } else {
            // looking for p in the left subtree
            TreeNode left = inorderSuccessor(root.left, p);
            //null case: when leftmost leaf => root is next bigger node
            return left != null ? left : root;
        }
    }
}

/*
similiar for predecessor:
*/
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return root;
        }

        if (root.val >= p.val) {
            TreeNode left = inorderSuccessor(root.left, p);
            return left;
        } else {
            TreeNode right = inorderSuccessor(root.right, p);
            return right != null ? right : root;
        }
    }
}
