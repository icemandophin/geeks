/*
Trie approach:
build trie from dict
split sentence to words
for each word, search in trie and find shortest prefix match
not found => append orignal word
found => append found prefix
*/
public class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || sentence == null) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        // build trie
        Trie trie = new Trie();
        for (String w : dict) {
            trie.insert(w);
        }
        // split input into words
        String[] words = sentence.split(" ");
        // check and replace each word if its prefix exist in dict
        for (String word : words) {
            // search and replace the result
            res.append(trie.replace(word));
            res.append(" ");
        }
        // remove last " "
        res.setLength(res.length() - 1);

        return res.toString();
    }
}

class TrieNode {
    public char val;
    public boolean isWord;
    // by default each child is null
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
    TrieNode(char c) {
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode cur = root;
        char[] ch = word.toCharArray();
        for (char c : ch) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }
    // search word in Trie: if its prefix match return prefix
    // else return origin word
    public String replace(String str) {
        StringBuilder res = new StringBuilder("");
        TrieNode cur = root;
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (cur.children[c - 'a'] == null) {
                // there is mismatch
                return str;
            }
            // add cur char to res
            res.append(c);
            cur = cur.children[c - 'a'];
            // check if cur res is a prefix in dict
            if (cur.isWord) {
                break;
            }
        }
        return res.toString();
    }
}
