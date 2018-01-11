/*
MSB on head => cannot directly add
node i depends on node i + 1 => push a, b to stack
then pop out and add like leetcode #2
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> ak = new Stack<>();
        Stack<Integer> bk = new Stack<>();
        // push l1
        while (l1 != null) {
            ak.push(l1.val);
            l1 = l1.next;
        }
        // push l2
        while (l2 != null) {
            bk.push(l2.val);
            l2 = l2.next;
        }
        // res will be dummy.next
        ListNode dummy = new ListNode(0);
        int carry = 0;
        while (!ak.empty() || !bk.empty() || carry > 0) {
            // get prev carry bit
            int sum = carry;
            sum += ak.empty() ? 0 : ak.pop();
            sum += bk.empty() ? 0 : bk.pop();
            // update carry
            carry = sum / 10;
            // new node need to be added to left of existing nodes
            ListNode node = new ListNode(sum % 10);
            // dummy.next always points to cur MSB node
            node.next = dummy.next;
            dummy.next = node;
        }

        return dummy.next;
    }
}
