/*
Basic: LC 140 + LC 17
Optimized: build trie with number
heavy call/high qps or large input/long MatchWords
=> cache results in HashMap
*/
import java.util.*;
// System.out.println("res: ");

import javax.activation.MailcapCommandMap;

public class Solution {
	public static void main(String[] args) {
		String digits = "463";
		List<String> dict = new ArrayList<>();
		dict.add("god");
		dict.add("dog");
		dict.add("cat");

		MatchWords test = new MatchWords();
		List<String> strs = test.findMatch(dict, digits);
		for (String s : strs) {
			System.out.println(s);
		}
	}
}

class MatchWords {
	public List<String> findMatch(List<String> dict, String digits) {
		Trie tree = new Trie();
		tree.load(dict);
		String[] pad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		List<String> res = new ArrayList<>();

		dfs(tree.root, pad, digits, 0, res);

		return res;
	}

	private void dfs(Node root, String[] pad, String num, int idx, List<String> res) {
		int n = num.length();
		if (idx == n) {
			return;
		}
		// match cur num
		String match = pad[Character.getNumericValue(num.charAt(idx))];
		for (char x : match.toCharArray()) {
			if (root.ch[x - 'a'] != null) {
				// add word when digits match word
				Node cur = root.ch[x - 'a'];
				if (idx == n - 1 && !cur.word.equals("")) {
					res.add(cur.word);
				}
				// match next digits
				dfs(cur, pad, num, idx + 1, res);
			}
		}
	}
}

class Node {
	Node[] ch;
	String word;

	public Node() {
		ch = new Node[26];
		word = "";
	}
}

class Trie {
	public Node root;

	public Trie() {
		root = new Node();
	}

	public void load(List<String> dict) {
		for (String word : dict) {
			Node cur = root;

			for (char w : word.toCharArray()) {
				if (cur.ch[w - 'a'] == null) {
					cur.ch[w - 'a'] = new Node();
				}
				cur = cur.ch[w - 'a'];
			}

			cur.word = word;
		}
	}
}
