/*
recur solution: check if s or s's subtree is the same tree as t
*/
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        } else {
            // check if s and t the same
            // or t exist in left/right substree
            return sameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }
    // check if s and t are same
    private boolean sameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null)  {
            return false;
        } else {
            return s.val == t.val && sameTree(s.left, t.left) && sameTree(s.right, t.right);
        }
    }
}

/*
similar to LC 297 - convert tree to string
then check if one is substring of another
can utilize LIB API
*/
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String ss = serialize(s);
        String ts = serialize(t);

        return ss.indexOf(ts) >= 0;
    }
    // tree -> str with pre-order
    private String serialize(TreeNode s) {
        if (s == null) {
            return "#";
        }

        return " " + s.val + serialize(s.left) + serialize(s.right);
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
