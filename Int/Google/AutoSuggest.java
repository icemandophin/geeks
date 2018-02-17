package solution;

import java.util.*;
// System.out.println("res: ");
import javax.activation.MailcapCommandMap;

public class Solution {
	public static void main(String[] args) {
		String[] dict = new String[] {
				"abc",
				"abd",
				"acef",
				"adg",
				"aces",
				"acek",
				"acz",
				"ac"
		};

		AutoSuggest test = new AutoSuggest(dict);
		System.out.println(test.suggest("ac"));
	}
}

class TrieNode {
	char c;
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	boolean isWord;
	String word;
	public TrieNode(char c) {
		this.c = c;
	}
}

class AutoSuggest {
	private TrieNode root;

	public AutoSuggest(String[] dict) {
		root = new TrieNode('*');
		// build trie from dict
		for (String word : dict) {
			insert(word);
		}
	}
	// return list of words that match prefix
	public List<String> suggest(String prefix) {
		List<String> res = new ArrayList<>();
		TrieNode cur = root;
		HashMap<Character, TrieNode> curChild = root.children;
		char[] arr = prefix.toCharArray();
		// check prefix match exist
		for (int i = 0; i < arr.length; ++i) {
			char c = arr[i];
			if (!curChild.containsKey(c)) {
				// cannot match prefix
				// return empty list
				return res;
			}
			// move to next char
			cur = curChild.get(c);
			curChild = cur.children;
		}
		// prefix met, dfs all matching words
		// check if prefix itself is word
		if (cur.isWord) {
			res.add(cur.word);
		}
		// check following words
		for (TrieNode start : curChild.values()) {
			dfs(start, res);
		}

		return res;
	}
	// find all words under cur TrieNode
	private void dfs (TrieNode cur, List<String> res) {
		if (cur.isWord) {
			res.add(cur.word);
		}

		HashMap<Character, TrieNode> curChild = cur.children;

		for (TrieNode node : curChild.values()) {
			dfs(node, res);
		}
	}

	public void insert(String word) {
		TrieNode cur = root;
		char[] arr = word.toCharArray();
		for (int i = 0; i < arr.length; ++i) {
			char wc = arr[i];
			HashMap<Character, TrieNode> curChild = cur.children;

			if (!curChild.containsKey(wc)) {
				TrieNode add = new TrieNode(wc);
				curChild.put(wc, add);
			}
			cur = curChild.get(wc);
		}
		// mark word ending and save word
		cur.isWord = true;
		cur.word = word;
	}
}
