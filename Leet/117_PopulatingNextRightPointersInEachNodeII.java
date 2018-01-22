/*
iterative with constant space:
use lv to refer 1st node on eavh level
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

/*
level BFS - O(N) space
enqueue each node left -> right => order by nature
record queue size at start => know when to jump to next level
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode pre = null;

            for (int i = 0; i < size; i++) {
                TreeLinkNode curr = queue.poll();

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }

                if (pre != null) {
                    pre.next = curr;
                }

                pre = curr;
            }
        }
    }
}
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
