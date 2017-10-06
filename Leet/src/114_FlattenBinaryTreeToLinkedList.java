/*
iterative approach:
for each node, if it has left tree(ol), make it new right tree of node
and make original right tree(or) the right tree of ol's rightmost child
then move to current node's right child and continue
*/
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // start traverse from root
        TreeNode cur = root;
        while (cur != null) {
            // if there is left tree
            // concatenate node -R- left -R- right
            if (cur.left != null) {
                // find rightmost node of ol
                TreeNode end = cur.left;
                while (end.right != null) {
                    end = end.right;
                }
                // make or rightmost node's right child
                end.right = cur.right;
                // link new right tree to node and remove left tree
                cur.right = cur.left;
                cur.left = null;
            }
            // move onto node's right children
            cur = cur.right;
        }
    }
}

/*
recursive approach: get idea from example
for every root, flatten its left and right child (fl and fr)
then make root.right = fl, rightMost(fl).right = fr
*/
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // flatten left and right child
        flatten(root.left);
        flatten(root.right);
        // shift fl to right subtree of root
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        // find rightmost node in new right child
        TreeNode cur = root.right;
        if (cur == null) {
            // root has no right tree
            // will link to original fr
            cur = root;
        }
        else {
            // find rightmost node
            while (cur.right != null) {
                cur = cur.right;
            }
        }
        // link rightmost node with original fr
        cur.right = tmp;
    }
}

/*
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
