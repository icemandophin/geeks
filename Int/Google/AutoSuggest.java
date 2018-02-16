Please use this Google doc to code during your interview. To free your hands for coding, we recommend that you use a headset or a phone with speaker option.


public class AutoSuggest {
	private TrieNode root;
	public AutoSuggest(String[] dict) {
		root = new TrieNode(‘*’);

		for (String word : dict) {
			insert(word);
}
}

public List<String> suggest(String prefix) {
	List<String> res = new ArrayList<>();
	TrieNode cur = root;
	HashMap<Character, TrieNode> curChild = root.children;
	char[] arr = prefix.toCharArray();

	for (int i = 0; i < arr.length; ++i) {
		char c = arr[i];
if (!curChild.containsKey(c)) {
	return res;
}
cur = curChild.get(c);
cur.child = cur.children;
}

if (cur.isWord) {
	res.add(prefix);
}

for (TrieNode child : cur.children) {
	String cur = “”;
	dfs(child, cur, res);
}
}

private void dfs (TrieNode cur, String pre, List<String> res) {

	if (pre.isWord) {
		res.add(cur + root);
		return;
}

HashMap<Character, TrieNode> curChild = cur.children;
for (Map.Entry<Character, TrieNode> child : curChild.entrySet()) {
dfs(child.getValue(), cur, res);
}
}

public void insert(String word) {
	TrieNode cur = root;
char arr = word.toCharArray();
int i;
char wc;
for (i = 0; i < arr.length; ++i) {
	wc = arr[i];
HashMap<Character, TrieNode> curChild = cur.children;
if (!curChild.containsKey(wc)) {
	TrieNode add = new TrieNode(wc);
curChild.put(wc, add);
}
cur = curChild.get(wc);
}

cur.isWord = true;
}
}

class TrieNode {
	char c;
HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
boolean isWord;
public TrieNode(char c) {
this.c = c;
}
}
