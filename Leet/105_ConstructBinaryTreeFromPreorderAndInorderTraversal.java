/*
pre[0] is root => find it in in[] and partition to left/right subtree
left: in[is : i-1] and pre[ps+1 : ps+i-is]
right: in[i+1 : ie] and pre[ps+i-is+1 : pe]
*/

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
    public TreeNode buildTree(int[] pre, int[] in) {
        return buildHelper(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    // recur build tree from pre-order and in-order array
    private TreeNode buildHelper(int[] pre, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe || is > ie) {
            return null;
        }
        // find current root/pre[ps] node in in[]
        int i = is;
        while (i <= ie) {
            if (in[i] == pre[ps]) {
                break;
            }
            i++;
        }
        // in[i] partitions left/right subtree
        TreeNode root = new TreeNode(pre[ps]);
        root.left = buildHelper(pre, ps + 1, ps + i - is, in, is, i - 1);
        root.right = buildHelper(pre, ps + i - is + 1, pe, in, i + 1, ie);

        return root;
    }

}
