/*
recursive: divide list to root and remaining part
*/
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}

/*
iterative with stack: reverse order
*/
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        Stack<ListNode> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        head = stack.peek();

        while (!stack.isEmpty()) {
            ListNode peek = stack.pop();
            peek.next = stack.isEmpty() ? null : stack.peek();
        }

        return head;
    }
}

/*
improved with O(1) space: similar to swap op:
// save next (for cur pointer)
Node tmp = cur.next
// reverse direction
cur.next = pre
// move pre and cur forward
pre = cur
cur = tmp
*/
public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }
}
