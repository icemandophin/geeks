/*
iterative: cur and cur.next
 */

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if((head == null) || (head.next == null))
        {
            return head;
        }

        ListNode cur = head;
        while((cur != null) && (cur.next != null))
        {
            if(cur.val == cur.next.val)
            {
                cur.next = cur.next.next;
            }
            else
            {
                cur = cur.next;
            }
        }

        return head;
    }
}

/*
another approach:
update x.next during each iteration
handle 1->1 and 1->1->1->1->1 scenario
*/
public class Solution {
	public ListNode deleteDuplicates(ListNode a) {
	    if (a == null || a.next == null) {
	        return a;
	    }

	    ListNode x = a;
	    ListNode y = a.next;

	    while (y != null) {
	        if (x.val == y.val) {
	            x.next = y.next;
	        } else {
	            x = y;
	        }
	        y = y.next;
	    }

	    return a;
	}
}
