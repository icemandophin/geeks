/*
iterative:
1. start with dummy node
2. swap next two and move head forward
*/
public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) {
            ListNode pre = head.next;
            ListNode curr = head.next.next;
            pre.next = curr.next;
            curr.next = head.next;
            head.next = curr;
            head = pre;
        }

        return dummy.next;
    }
}

/*
recursive:
1. swap top 2
2. connect with recur remainging
*/
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tmp = head.next;
        head.next = swapPairs(head.next.next);
        tmp.next = head;

        return tmp;
    }
}
