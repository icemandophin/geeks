class Solution {
    public List<String> findWords(char[][] board, String[] words) {

    }
}

class Node {
    public char val;
    public Node[] next;
    public String word;

    public Node() {
        next = new Node[26];
        word = "";
    }
}

class Trie{
    public root;

    public Trie() {
        root = new Node();
    }

    public void insert(String[] words) {
        for (String word : words) {
            Node cur = root;

            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                if (cur.next[ch - 'a'] == null) {
                    cur.next[ch - 'a'] = new Node();
                }
                cur = cur.next[ch - 'a'];
            }

            cur.word = word;
        }
    }
}
