import java.util.*;

public class Solution {
	public static void main(String[] args) {
		FileSystem obj = new FileSystem();
		display(obj.ls("/"));
		obj.mkdir("/a/b/c");
		obj.addContentToFile("/a/b/c/d", "hello");
		display(obj.ls("/"));
		display(obj.ls("/a/b/c"));
		System.out.println(obj.readContentFromFile("/a/b/c/d"));
	}

	private static void display(List<String> strs) {
		for (String str : strs) {
			System.out.println(str);
		}
	}
}

class FileSystem {
	// file can represent instance or directory
	private class File {
		public boolean isFile;
		// store file / folder like trie
		public Map<String, File> files;
		public String content;

		public File() {
			isFile = false;
			files = new HashMap<>();
			content = "";
		}
	}

	private File root;

	public FileSystem() {
		root = new File();
	}

	public List<String> ls(String path) {
		File cur = root;
		List<String> res = new ArrayList<>();

		if (!path.equals("/")) {
			String[] strs = path.split("/");
			for (int i = 1; i < strs.length; ++i) {
				// move to the last level
				cur = cur.files.get(strs[i]);
			}
			// file points to last file/dir when exit loop
			if (cur.isFile) {
				res.add(strs[strs.length - 1]);

				return res;
			}
		}
		// add all sub directories into res
		res = new ArrayList<>(cur.files.keySet());
		// ensure lexicographic order
		Collections.sort(res);

		return res;
	}

	public void mkdir(String path) {
		File cur = root;
		String[] str = path.split("/");

		for (int i = 1; i < str.length; ++i) {
			cur.files.putIfAbsent(str[i], new File());
			cur = cur.files.get(str[i]);
		}
	}

	public void addContentToFile(String path, String content) {
		File cur = root;
		String[] str = path.split("/");

		for (int i = 1; i < str.length; ++i) {
			cur.files.putIfAbsent(str[i], new File());
			cur = cur.files.get(str[i]);
		}

		cur.isFile = true;
		cur.content += content;
	}

	public String readContentFromFile(String path) {
		File cur = root;
		String[] str = path.split("/");

		for (int i = 1; i < str.length; ++i) {
			cur = cur.files.get(str[i]);
		}

		return cur.content;
	}
}
