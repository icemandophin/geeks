class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isWord;
    public TrieNode() {}
    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;
    // init root node as empty
    public Trie() {
        root = new TrieNode();
    }
    // insert word into trie
    public void insert(String word) {
        TrieNode cur = root;
        char[] wordArray = word.toCharArray();
        int i;
        char wc;
        for (i = 0; i< wordArray.length; ++i) {
            wc = wordArray[i];
            HashMap<Character, TrieNode> curChild = cur.children;
            if (curChild.containsKey(wc)) {
                cur = curChild.get(wc);
            }
            else {
                TrieNode addNode = new TrieNode(wc);
                curChild.put(wc, addNode);
                cur = addNode;
            }
        }
    }
    // check if word is in the trie
    public boolean search(String word) {
        if (SearchPrefix(word) == null) {
            return false;
        }
        else if (SearchUtil(word).isWord) {
            return true;
        }
        else {
            return false;
        }
    }
    // search for word start with str in the Trie
    public TrieNode SearchPrefix(String str) {
        HashMap<Character, TrieNode> curChild = root.children;
        TrieNode cur = root;
        char[] strArr = str.toCharArray();
        int i;
        char c;
        for (i = 0; i < strArr.length; ++i) {
            c = strArr[i];
            if (curChild.containsKey(c)) {
                cur = curChild.get(c);
                curChild = cur.children;
            }
            else {
                return false;
            }
        }
        return cur;
    }
}
