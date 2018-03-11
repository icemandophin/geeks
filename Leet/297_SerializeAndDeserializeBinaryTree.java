/*
iterative approach: BFS with queue
dequeue new node => visit node => enqueue its children
keep in loop until queue is empty
use "#" for empty node, " " to separate value of each node
use String Builder since string is immutable/cannot be edited
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

/*
recursive approach: pre-order/dfs
define symbol for null node and ending symbol
encode and decode using the same traversal sequence
*/
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> res = new ArrayList<>();
        preOrder(root, res);
        // concatenate sub-result strings
        // use " " to separate value of each node
        return String.join(" ", res);
    }

    private void preOrder(TreeNode root, List<String> list) {
        if (root == null) {
            // use # to indicate null node
            list.add("#");
            return;
        }
        // add root first, then recursive traverse left/right subtree
        list.add(root.val + " ");
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return decode(Arrays.asList(data.split(" ")).iterator());
    }

    private TreeNode decode(Iterator<String> it) {
        String str = it.next();
        if (str.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = decode(it);
        root.right = decode(it);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
