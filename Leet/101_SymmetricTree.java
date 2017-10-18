/*
iterative way:
*/

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
        {
            return true;
        }

        Stack<TreeNode> sk = new Stack<TreeNode>();
        TreeNode left, right;

        if(root.left != null)
        {
            if(root.right == null)
            {
                return false;
            }
            else
            {
                sk.push(root.left);
                sk.push(root.right);
            }
        }
        else if(root.right != null)
        {
            return false;
        }

        while(!sk.empty())
        {
            if(sk.size()%2 != 0)
            {
                // not balance
                return false;
            }

            right = sk.pop();
            left = sk.pop();
            if(right.val != left.val)
            {
                return false;
            }

            if(left.left != null)
            {
                if(right.right == null)
                {
                    return false;
                }
                else
                {
                    sk.push(left.left);
                    sk.push(right.right);
                }
            }
            else if(right.right != null)
            {
                return false;
            }

            if(left.right != null)
            {
                if(right.left == null)
                {
                    return false;
                }
                else
                {
                    sk.push(left.right);
                    sk.push(right.left);
                }
            }
            else if(right.left != null)
            {
                return false;
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
