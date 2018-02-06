/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            double sum = 0;

            for (int i = 0; i < count; i++) {
                TreeNode peek = queue.poll();
                sum += peek.val;

                if (peek.left != null) {
                    queue.offer(peek.left);
                }

                if (peek.right != null) {
                    queue.offer(peek.right);
                }
            }

            res.add(sum / count);
        }

        return res;
    }
}
