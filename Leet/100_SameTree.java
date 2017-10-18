/*
Iterative: utilize stack - BFS
For each iteration:
compare root by value
compare left/right subtree by stack size
*/
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> sp = new Stack <> ();
        Stack<TreeNode> sq = new Stack <> ();
        TreeNode pn = new TreeNode(0);
        TreeNode qn = new TreeNode(0);

        if(p != null)
        {
            sp.push(p);
        }

        if(q != null)
        {
            sq.push(q);
        }

        while(!sp.isEmpty() && !sq.isEmpty())
        {
            pn = sp.pop();
            qn = sq.pop();
            if(pn.val != qn.val){return false ;}

            if (pn.right != null) {sp.push(pn.right) ;}
            if (qn.right != null) {sq.push(qn.right) ;}
            if (sp.size() != sq.size()) {return false ;}

            if (pn.left != null) {sp.push(pn.left) ;}
            if (qn.left != null) {sq.push(qn.left) ;}
            if (sp.size() != sq.size()) {return false ;}
        }

        return (sp.size() == sq.size());
    }
}


/*
Recursive: DFS approach
cover corner cases
*/

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if((p == null) && (q == null))
        {
            return true;
        }
        else if((p == null) || (q == null))
        {
            return false;
        }
        else
        {
            boolean res = true;

            if(p.val != q.val)
            {
                res = false;
            }

            res &= isSameTree(p.left, q.left);
            res &= isSameTree(p.right, q.right);

            return res;
        }
    }
}
