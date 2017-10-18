/*
since BST is also binary tree, can use same sloution as 297
put iterative BST with queue solution here
*/
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> q = new LinkedList<>();
        // create string builder to generate final result
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                // notice: for empty node, need to insert both
                // "#"(mark empty node) and " " (separator between node values)
                res.append("# ");
                continue;
            }
            // handle node, then enqueue all its children
            res.append(cur.val + " ");
            q.add(cur.left);
            q.add(cur.right);
        }
        // convert builder to immutable string
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        Queue<TreeNode> q = new LinkedList<>();
        // cut string and save value of each node
        String[] strs = data.split(" ");
        // convert string to int value
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        // 1st element must be the root
        q.add(root);
        // utilize queue to decode nodes from list of strings
        for (int i = 1; i < strs.length; ++i) {
            TreeNode cur = q.poll();
            if (!strs[i].equals("#")) {
                // left child is not empty
                TreeNode left = new TreeNode(Integer.parseInt(strs[i]));
                cur.left = left;
                // enqueue child for next layer
                q.add(left);
            }
            if (!strs[++i].equals("#")) {
                // right child is not empty
                TreeNode right = new TreeNode(Integer.parseInt(strs[i]));
                cur.right = right;
                // enqueue child for next layer
                q.add(right);
            }
        }
        return root;
    }
}
