/*
for perfect binary tree: there are 2 * N + 1 nodes in first N layers
for parent i, left child is 2 * i, right child is 2 * i + 1
=> assign index for each node: maxWidth = Max{rightIdx - leftIdx + 1}
   where leftIdx/rightIdx is first/last node in each layer
*/
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        // root has depth 0, index 1
        return getWidth(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
    }

    // get max length for given tree
    // record index of start/end node for each level
    private int getWidth(TreeNode root, int level, int idx, List<Integer> top, List<Integer> end) {
        if (root == null) {
            return 0;
        }
        // when level == top.size() => complete traverse in prev level
        if (level == top.size()) {
            top.add(idx);
            // also init end, in case there is one node in this level
            end.add(idx);
        } else {
            // update index of last node
            end.set(level, idx);
        }
        // get width of cur level
        int cur = end.get(level) - top.get(level) + 1;
        // get width in left/right subtree
        int left = getWidth(root.left, level + 1, idx * 2, top, end);
        int right = getWidth(root.right, level + 1, idx * 2 + 1, top, end);

        return Math.max(Math.max(left, right), cur);
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
