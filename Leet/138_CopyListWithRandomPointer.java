/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

/*
hashmap: extra O(n) space
1. build copy of each node (A'.next = A.next) and add to hashmap A -> A'
2. for each node in copy update A'.next/random = map.get(A'.next/random)
*/
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        // copy list
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode copy = clone(cur);
            map.put(cur, copy);
            cur = cur.next;
        }
        // adjust copy list pointer
        RandomListNode res = map.get(head);
        cur = res;
        while (cur != null) {
            cur.next = map.get(cur.next);
            cur.random = map.get(cur.random);
            cur = cur.next;
        }

        return res;
    }

    private RandomListNode clone(RandomListNode node) {
        if (node == null) {
            return null;
        }

        RandomListNode res = new RandomListNode(node.label);
        res.next = node.next;
        res.random = node.random;

        return res;
    }
}

/*
copy without hashmap - extra traversal:
1. insert cloned node between original node: A-A'-B-B'-C-C'
2. adjust random ptr: node.next.random = node.random.next
3. separate A and A': node.next = node.next.next
*/
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }

        RandomListNode curr = head;
        // insert cloned node
        while (curr != null) {
            RandomListNode copy = new RandomListNode(curr.label);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        // adjust random ptr
        curr = head;
        while (curr != null) {
            RandomListNode copy = curr.next;

            if (curr.random != null) {
                copy.random = curr.random.next;
            }

            curr = copy.next;
        }
        // adjust next ptr
        RandomListNode next = head.next;
        curr = next;
        while (head != null) {
            head.next = head.next.next;

            if (curr.next != null) {
                curr.next = curr.next.next;
            }

            head = head.next;
            curr = curr.next;
        }

        return next;
    }
}
