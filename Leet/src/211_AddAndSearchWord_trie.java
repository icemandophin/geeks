/*
follow-up from 208
create recursive match function
For '.' search, just check if any of children can match the substring
*/

public class WordDictionary {
    //define struct of trie node
    public class TrieNode
    {
        public TrieNode[] children = new TrieNode[26];
        public boolean isWord = false;
    }

    private TrieNode root = new TrieNode();

    /** Initialize your data structure here. */
    public WordDictionary() {}

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        int i;
        char c;
        for (i=0; i<word.length(); ++i)
        {
            c = word.charAt(i);
            if(cur.children[c-'a'] == null)
            {
                // no this char at spot, add it was new child
                cur.children[c-'a'] = new TrieNode();
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
    private boolean match(char[] str, int k, TrieNode cur)
    {
        if (k == str.length)
        {
            // matched to last char, check the end flag
            return cur.isWord;
        }

        if (str[k] != '.')
        {
            // check (1) current char exist in path (2) recursively check substring
            return ((cur.children[str[k]-'a'] != null) && match(str, k+1, cur.children[str[k]-'a']));
        }
        else
        {
            // for wild symbol, check:
            // (1) current char is valid
            // (2) traverse all valid children of cur, and return true if any of them can match substring
            for (int i=0; i<cur.children.length; ++i)
            {
                if (cur.children[i] != null)
                {
                    if (match(str, k+1, cur.children[i]))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);

    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
