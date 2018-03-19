package solution;
import java.util.*;

/*
 * two iterators: one traverse list, the other traverse each element of cur list
 */
public class Solution {
	public static void main(String[] args) {
		Tenary t = new Tenary();
		// a?b?c:d:e
		t.convert("a?b?d?f:g?h:i:e:c?j?k:l:m");
	}
}

class Tenary {
	public TreeNode convert(String s) {
		Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
		// create node for each element
		for(int i = 0; i < s.length(); i += 2) {
			map.put(i, new TreeNode(s.charAt(i)));
		}

		int i = 1, N = s.length();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (i < N) {
			// push for each "?"
			if (i < N && s.charAt(i) == '?') {
				// get root node
				TreeNode p = getNode(map, i - 1);
				// check and connect p with its parent
				if(i - 2 > -1 && !stack.isEmpty() && s.charAt(i - 2) != ':') {
					stack.peek().left = p;
				}
				stack.push(p);
				// move to next sign
				i += 2;
			} else {
				// pop for each ":"
				// get stack top as parent
				TreeNode top = stack.pop();
				TreeNode right = getNode(map, i + 1);
				top.right = right;
				if(i - 2 > -1 && s.charAt(i - 2) != ':') {
					TreeNode left = getNode(map, i - 1);
					top.left = left;
				}
				// move to next sign
				i += 2;
			}
		}

		return map.get(0);
	}

	private TreeNode getNode(Map<Integer, TreeNode> map, int k) {
		// TODO Auto-generated method stub
		return map.get(k);
	}
}

class TreeNode {
	char val;
	TreeNode left;
	TreeNode right;
	TreeNode(char x){
		val = x;
	}
}
