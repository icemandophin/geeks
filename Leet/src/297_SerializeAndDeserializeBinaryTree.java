/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

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
        list.add(root.val + "");
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
