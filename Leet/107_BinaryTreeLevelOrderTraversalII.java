/*
similar to #102 - still traverse top -> buttom
insert cur layer to top of res instead of last
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
            // add cur level to top of res
            // shift original layers to next position
            res.add(0, cur);
        }

        return res;
    }
}
