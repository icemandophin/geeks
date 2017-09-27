/*
LRU - evict least recently used item
double linked list approach: O(1) for add/remove node
nodes in list are ranked by oldest -> latest during read
add dummy node at top & end as boundary
build HashMap to find keys in O(1)
keep private size of Redis cache
*/
class LRUCache {
    private class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
    private int size;
    private HashMap<Integer, Node> hash;
    private Node top;
    private Node end;

    public LRUCache(int size) {
        this.size = size;
        this.hash = new HashMap<Integer, Node>();
        this.top = new Node(-1, -1);
        this.end = new Node(-1, -1);
        this.top.next = this.end;
        this.end.prev = this.top;
    }
    // first check item/key exist in list
    // for each read, need to make item "fresh"
    public int get(int key) {
        if (!hash.containsKey(key)) {
            return -1;
        }
        // remove existing pair, and add it on end
        Node cur = hash.get(key);
        // node can be deleted without referring other nodes
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        addOnTail(cur);
        return cur.val;
    }

    private void addOnTail(Node node) {
        node.next = end;
        node.prev = end.prev;
        end.prev.next = node;
        end.prev = node;
    }
    // if item/key exist, just update its value
    // if not, need to insert to BOTH list and hash
    // also need to handle cache full scenario (remove in both list and hash)
    public void put(int key, int value) {
        if (get(key) != -1) {
            // node exist, just update val
            hash.get(key).val = value;
            return;
        }
        // handle overflow with ranked list
        if (hash.size() == size) {
            hash.remove(top.next.key);
            top.next = top.next.next;
            top.next.prev = top;
        }
        // add new node to hash and list
        Node cur = new Node(key, value);
        hash.put(key, cur);
        addOnTail(cur);
    }
}
