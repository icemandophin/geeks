/*
1. Each Trie node has 26 children (all possible char in a word)
2. Make root empty, each word start from 2nd layer/children of root
3. use flag to indicate whether current node is the end of a word
*/

class TrieNode {
    public char val;
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
    TrieNode(char c){
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }


    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        int i;
        char c;
        for (i=0; i<word.length(); ++i)
        {
            c = word.charAt(i);
            if(cur.children[c-'a'] == null)
            {
                // no this char at spot, add it was new child
                cur.children[c-'a'] = new TrieNode(c);
            }
            else
            {
                // this char already in correct path, continue
            }
            // move cur pointer down
            cur = cur.children[c-'a'];
        }
        // word complete, set end flag
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        int i;
        char c;

        for (i=0; i<word.length(); ++i)
        {
            c = word.charAt(i);
            if(cur.children[c-'a'] == null)
            {
                // no match found in current dictionary, return false
                return false;
            }
            else
            {
                // continue
            }
            // update cur pointer to next layer
            cur = cur.children[c-'a'];
        }

        // all chars found, now check if last char is marked as end of a word
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        //similiar to search method, but skip the check of word ending
        TrieNode cur = root;
        int i;
        char c;

        for (i=0; i<prefix.length(); ++i)
        {
            c = prefix.charAt(i);
            if(cur.children[c-'a'] == null)
            {
                return false;
            }
            else
            {}
            cur = cur.children[c-'a'];
        }
        return true;
    }
}
