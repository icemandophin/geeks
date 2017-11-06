/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    ListNode root;
    Random rand;
    // constructor
    public Solution(ListNode head) {
        this.root = head;
        this.rand = new Random();
    }
    /** Returns a random node's value. */
    public int getRandom() {
        if (root == null) {
            return -1;
        }
        ListNode cur = root;
        int res = root.val;
        // i is also the counter of nodes that has visited
        for(int i=1; cur.next != null; i++) {
	        cur = cur.next;
            if (rand.nextInt(i + 1) == i) {
                // hit cur node with possibility 1/n
                // update res
                res = cur.val;
            }
        }

        return res;
    }
}
