/*
no root, has parent pointer
*/
public class Solution {
    public TreeNode nextLargest(TreeNode t) {
        TreeNode temp = t; //没有最大值返回本身
        if(t.right != null) return getLeftMost(t.right);

        TreeNode parent = t.parent;
        while(parent != null && t.data > parent.data) {
            t = parent;
            parent = parent.parent;
        }

        return parent;
    }

    private TreeNode getLeftMost(TreeNode root) {
        TreeNode p = root;
        while(p.left != null) {
            p = p.left;
        }

        return p;
    }
}

/*
no parent pointer, given root
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
