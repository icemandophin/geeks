/*
iterative approach:
BFS with queue and swap each node's left and right child
*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;

            if (cur.left != null) {
                q.add(cur.left);
            }

            if (cur.right != null) {
                q.add(cur.right);
            }
        }

        return root;
    }
}

/*
recursive approach:
*/
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;

        return root;
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
