class Solution {
    public List<TreeNode> deleteNodes(TreeNode root, Set<Integer> set) {
        List<TreeNode> res = new ArrayList<>();

        if (deleteNodes(root, set, res) != null) {
            res.add(root);
        }

        return res;
    }

    private TreeNode deleteNodes(TreeNode root, Set<Integer> set, List<TreeNode> res) {
        if (root == null) {
            return null;
        }

        root.left = deleteNodes(root.left, set, res);
        root.right = deleteNodes(root.right, set, res);

        if (set.contains(root.val)) {
            if (root.left != null) {
                res.add(root.left);
            }

            if (root.right != null) {
                res.add(root.right);
            }

            return null;
        } else {
            return root;
        }
    }
}
