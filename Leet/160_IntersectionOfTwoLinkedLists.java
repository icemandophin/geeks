/*
suppose diatance between head and intersection node is a and b
from intersection to common end is c
when shorter one(pa) reach end, total distance is a+c => pb is (b-a) from end
reset pa to root of list b, and reset pb to start of list a when it arrives end
when pa and pb meet, they both move additional b and (b-a) + a steps
that is intersection
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            // for the end of first iteration
            // just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }
}
