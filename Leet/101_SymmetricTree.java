/*
iterative way:
level order BFS and compare a.left with b.right
*/
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode peek1 = queue.poll();
            TreeNode peek2 = queue.poll();

            if (peek1 == null && peek2 == null) {
                continue;
            } else if (peek1 == null || peek2 == null) {
                return false;
            } else if (peek1.val != peek2.val) {
                return false;
            } else {
                // enqueue left/right child in reverse order
                queue.add(peek1.left);
                queue.add(peek2.right);
                queue.add(peek1.right);
                queue.add(peek2.left);
            }
        }

        return true;
    }
}
/*
recursive way:
*/
class Solution {
        public boolean isSymmetric(TreeNode root) {
        return root==null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right){
        if(left==null || right==null)
            return left==right;
        if(left.val!=right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }
}
