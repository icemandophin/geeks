/*
iterative in-order traversal with stack
*/
public class BSTIterator {
    // stack always store next node
    private Stack<TreeNode> sk;

    public BSTIterator(TreeNode root) {
        // push all left node
        // when iterate - pop and push right node's left children
        sk = new Stack<>();
        push(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !sk.empty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = sk.pop();
        // push right's left
        push(cur.right);

        return cur.val;
    }

    // push cur node's left children to stack
    private void push(TreeNode cur) {
        while (cur != null) {
            sk.push(cur);
            cur = cur.left;
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
