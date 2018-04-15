/*
leftmost leaf will be new root
      1
     / \
    2  3
   /\
  4 5
=> root.left.left = root.right
   root.left.right = root
        pre: 1
            /
           2 - 3: tmp
          /
   next: 4 - 5
=>
     4
    / \
   5  2
     /\
    3 1
*/
/*
Iterative:
pre is previous root after repoint
tmp is right node of previous root.
Complexity: O(N)time O(1)space
*/
public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode next = null;
        TreeNode temp = null;
        TreeNode pre = null;

        while(root != null) {
            next = root.left;
            root.left = temp;
            temp = root.right;
            root.right = pre;
            pre = root;
            root = next;
        }

        return pre;
    }
}

/*
recursive:
find the leftmost node as the root.
Return repoint each new parent - root.left to previous root and root.right;
Complexity: O(N)time, O(N)space stack
*/
public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return newRoot;
    }
}
