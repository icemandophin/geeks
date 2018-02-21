/*
iterative with constant space:
use lv to refer 1st node on NEXT level
use cur to traverse each level and connect nodes
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        while (root != null) {
            // lv.next is the start node of each layer
            TreeLinkNode lv = new TreeLinkNode(-1);
            TreeLinkNode cur = lv;
            // level order traversal
            while (root != null) {
                // left -> right, right -> null
                if (root.left != null) {
                    // set left node as start of next layer
                    if (lv.next == null) {
                        lv.next = root.left;
                    }
                    cur.next = root.left;
                    cur = cur.next;
                }
                if (root.right != null) {
                    // set right node as start of next layer
                    // if it is not set to left node yet
                    if (lv.next == null) {
                        lv.next = root.right;
                    }
                    cur.next = root.right;
                    cur = cur.next;
                }

                root = root.next;
            }
            // move to next layer
            root = lv.next;
        }
    }
}
