/*
iterative: level order BFS with queue
flip flag to control whether reverse order or not
*/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        boolean rev = false;
        q.offer(root);
        // BFS traverse
        while (!q.isEmpty()) {
            int n = q.size();
            // make it linkedlist to enable add at head/end
            List<Integer> cur = new LinkedList<>();
            for (int i = 0; i < n; ++i) {
                TreeNode x = q.poll();
                // if rev is true => reverse insert elements for this level
                if (rev) {
                    cur.add(0, x.val);
                } else {
                    cur.add(cur.size(), x.val);
                }
                // insert children
                if (x.left != null) {
                    q.add(x.left);
                }
                if (x.right != null) {
                    q.add(x.right);
                }
            }
            // flip flag
            rev = !rev;
            res.add(cur);
        }

        return res;
    }
}

/*
recursive: use level number to flag reverse input
*/
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // start from level 0
        preorder(root, 0, result);

        return result;
    }

    private void preorder(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) {
            return;
        }

        if (result.size() == level) {
            result.add(new LinkedList<Integer>());
        }

        List<Integer> list = result.get(level);
        list.add(level % 2 == 0 ? list.size() : 0, root.val);

        preorder(root.left, level + 1, result);
        preorder(root.right, level + 1, result);
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
