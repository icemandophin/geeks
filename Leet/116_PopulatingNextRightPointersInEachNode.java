/*
level BFS - O(N) space
enqueue each node left -> right => order by nature
record queue size at start => know when to jump to next level
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeLinkNode> q = new LinkedList<>();
        q.offer(root);
        // level order traverse and make pre -> cur
        while (!q.isEmpty()) {
            int n = q.size();
            TreeLinkNode pre = null;
            // connect nodes of this level
            for (int i = 0; i < n; ++i) {
                TreeLinkNode cur = q.poll();

                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }

                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }
    }
}
