/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int cnt = 0;
        // get first k nodes
        while (cur != null && cnt != k) {
            cnt++;
            cur = cur.next;
        }

        if (cnt == k) {
            // revert remaining parts
            ListNode pre = reverseKGroup(cur, k);
            // revert head and next
            while (cnt-- > 0) {
                ListNode next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }

            return pre;
        } else {
            // complete - return
            return head;
        }
    }
}
