/*
min heap approach: time: O(nklogk) space: O(k)
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // build min heap of size k
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // add all head nodes into heap
        for (ListNode h : lists) {
            if (h != null) {
                pq.offer(h);
            }
        }
        // take min node out each time and add to res
        // then insert its next node back
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }
        // result start from dummy's next node
        return dummy.next;
    }
}

/*
divide and conquer:
if merge list1 & list2, then merge with list3, list4...
time will be 2n + 3n + ... + kn = O(nk^2) => TLE
optimize: merge lists 2 by 2 until only list left:
2n * k/2 + 4n * k/4 + ... + (2^x)n * k/(2^x) = nk * x
where x = log2(k) (tree length) => O(nklogk)
*/
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }

        return mergeHelper(lists, 0, lists.size() - 1);
    }

    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }

        int mid = start + (end - start) / 2;
        // merge top half and buttom half of lists separately
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }

    // merge list util method
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            // move to next node
            cur = cur.next;
        }
        // connect remaining nodes to res
        if (l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }

        return dummy.next;
    }
}
