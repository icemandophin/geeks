/*
fast check duplicate tree => traverse tree as hashing key
map pre-order_traverse_val_str -> num of occurance
*/
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        preOrder(root, map, res);

        return res;
    }
    // return pre-order traversal path of cur tree
    // update global tree mapping
    private String preOrder(TreeNode root, Map<String, Integer> map, List<TreeNode> res) {
        if (root == null) {
            return "#";
        }
        // build path of cur tree
        // separate val with space
        // duplicate in subtree is handled at the same time
        String path = root.val + " " + preOrder(root.left, map, res) + " " + preOrder(root.right, map, res);
        // get tree count
        int cnt = map.getOrDefault(path, 0);
        if (cnt == 1) {
            // first time hit duplicate
            res.add(root);
        }
        // update tree count
        map.put(path, cnt + 1);

        return path;
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
