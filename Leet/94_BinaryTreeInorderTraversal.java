// iterative with stack
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> sk = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !sk.isEmpty()) {
            if (cur != null) {
                sk.push(cur);
                cur = cur.left;
            } else {
                cur = sk.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }

        return res;
    }
}

// recursive
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);

        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }
}