public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();

        inorder(root, target, k, result);

        return result;
    }
    // inorder in BSZT => res[] is sorted
    private void inorder(TreeNode root, double target, int k, List<Integer> result) {
        if (root == null) {
            return;
        }
        // find k closet elements in left child
        inorder(root.left, target, k, result);
        // if res.size() < k => direct add
        // else compare top element with cur root
        // then add the smaller one
        if (result.size() == k) {
            if (Math.abs(root.val - target) < Math.abs(result.get(0) - target)) {
                result.remove(0);
            } else {
                return;
            }
        }

        result.add(root.val);
        // find k closet elements in right child
        inorder(root.right, target, k, result);
    }
}
