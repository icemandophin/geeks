/*
direct recursive takes O(n!) time...
=> build buttom-up to avoid repeated check and improve to O(n)

test case:
     10
    / \
   5  15
  / \   \
 1   8   7
*/
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Node input = new Node(10);
		input.left = new Node(5);
		input.right = new Node(15);
		input.left.left = new Node(1);
		input.left.right = new Node(8);
		input.right.right = new Node(7);

		System.out.println(LargestBST.LargestBSTSubtree(input));
	}
}

class LargestBST {
	public static int LargestBSTSubtree(Node root) {
		int[] res = new int[1];
		res[0] = 0;
        // dfs records global max size of BST contained in tree
		dfs(root, res);

		return res[0];
	}

    // dfs returns int[] which contains
    // {size of BST, min val of BST, max val of BST}
	private static int[] dfs(Node root, int[] res) {
		if (root == null) {
            // null children do not have restriction for parent
			return new int[] {0, Integer.MAX_VALUE, Integer.MIN_VALUE};
		}
        // get para of largest BST in left/right subtree
		int[] left = dfs(root.left, res);
		int[] right = dfs(root.right, res);

		if (root.val > left[2] && root.val < right[1]) {
			// cur tree makes BST, update result
			// and value scope of cur tree
			int min = Math.min(root.val, left[1]);
			int max = Math.max(root.val, right[2]);
			res[0] = Math.max(res[0], left[0] + right[0] + 1);

			return new int[] {left[0] + right[0] + 1, min, max};
		}

		// cur tree does not make BST
		// set val scope that no parent node can make BST => break check chain
		return new int[] {0, Integer.MIN_VALUE, Integer.MAX_VALUE};
	}
}

class Node {
	int val;
	Node left;
	Node right;
	Node(int x) { val = x; }
}
