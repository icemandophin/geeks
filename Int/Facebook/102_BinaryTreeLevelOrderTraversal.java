/*
BFS traverse with queue:
notice output should be per level
=> record cur queue size before adding children nodes
to guarantee only traverse each level
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            // get cur size before adding child node
            // avoid traverse node of next level
            int n = q.size();
            List<Integer> cur = new ArrayList<>();
            // BFS cur level
            for (int i = 0; i < n; ++i) {
                TreeNode peek = q.poll();
                cur.add(peek.val);

                if (peek.left != null) {
                    q.offer(peek.left);
                }

                if (peek.right != null) {
                    q.offer(peek.right);
                }
            }

            res.add(cur);
        }

        return res;
    }
}
