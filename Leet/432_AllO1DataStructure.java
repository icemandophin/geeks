/*
similar to LRU and time limited access:
double linked list by ascending order of count: each node contains hashset of keys whose count is val
HashMap(key -> cur_node) enables O(1) search of target key in list
=> when GetMax/MinKey(): go to top/end node
=> when Inc/Dec(Key): move key to neighbor node and add/remove node if necessary
*/
class AllOne {
    private class ListNode {
        // cnt of keys in this bucket
        public int val;
        // set of keys
        public Set<String> keySet;
        // double linked list for fast insert/remove
        public ListNode pre;
        public ListNode next;

        public ListNode(int val, Set<String> keySet) {
            this.val = val;
            this.keySet = keySet;
        }
    }
    // add dummy nodes on top/end to mark boundary
    // find max -> tail.prev, find min -> head.next
    private ListNode head;
    private ListNode tail;
    // fast locate node whose set contains target key
    private Map<String, ListNode> keyMap;
    /** Initialize your data structure here. */
    public AllOne() {
        head = new ListNode(0, new HashSet<>());
        tail = new ListNode(0, new HashSet<>());
        head.next = tail;
        tail.pre = head;
        keyMap = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (keyMap.containsKey(key)) {
            ListNode curr = keyMap.get(key);
            insertNext(curr, key);
            keyMap.put(key, curr.next);
            remove(curr, key);
        } else {
            insertNext(head, key);
            keyMap.put(key, head.next);
        }
    }
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (keyMap.containsKey(key)) {
            ListNode curr = keyMap.get(key);

            if (curr.val > 1) {
                insertPre(curr, key);
                keyMap.put(key, curr.pre);
            } else {
                keyMap.remove(key);
            }

            remove(curr, key);
        }
    }
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return head.next == tail ? "" : tail.pre.keySet.iterator().next();
    }
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail ? "" : head.next.keySet.iterator().next();
    }
    // put key to prev node of curr
    // create new node for new cnt
    private void insertPre(ListNode curr, String key) {
        int val = curr.val - 1;
        ListNode pre = curr.pre;

        if (pre.val == val) {
            pre.keySet.add(key);
        } else {
            insert(pre, curr, key, val);
        }
    }
    // put key to next node of curr
    // create new node for new cnt
    private void insertNext(ListNode curr, String key) {
        int val = curr.val + 1;
        ListNode next = curr.next;

        if (next.val == val) {
            next.keySet.add(key);
        } else {
            insert(curr, next, key, val);
        }
    }
    // insert new node between pre and next
    // add key to set and val as cnt
    private void insert(ListNode pre, ListNode next, String key, int val) {
        Set<String> keySet = new HashSet<String>();
        keySet.add(key);
        ListNode curr = new ListNode(val, keySet);
        pre.next = curr;
        curr.next = next;
        next.pre = curr;
        curr.pre = pre;
    }
    // remove key from set of node
    // remove node if set is empty
    private void remove(ListNode curr, String key) {
        curr.keySet.remove(key);

        if (curr.keySet.isEmpty()) {
            ListNode pre = curr.pre;
            ListNode next = curr.next;
            pre.next = next;
            next.pre = pre;
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
