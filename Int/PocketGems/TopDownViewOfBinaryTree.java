// Java program to print top view of Binary tree
import java.util.*;

// Class for a tree node
class TreeNode
{
	// Members
	int key;
	TreeNode left, right;

	// Constructor
	public TreeNode(int key)
	{
		this.key = key;
		left = right = null;
	}
}

// A class to represent a queue item. The queue is used to do Level
// order traversal. Every Queue item contains node and horizontal
// distance of node from root
class QItem
{
    TreeNode node;
    int hd;
    public QItem(TreeNode n, int h)
    {
    		node = n;
    		hd = h;
    }
}

// Class for a Binary Tree
class Tree
{
	TreeNode root;

	// Constructors
	public Tree() { root = null; }
	public Tree(TreeNode n) { root = n; }

	// This method prints nodes in top view of binary tree
	public List<Integer> topView()
	{
		List<Integer> res = new ArrayList<>();
		// base case
		if (root == null) { return res; }

		// Creates an empty hashset
		HashSet<Integer> set = new HashSet<>();

		// Create a queue and add root to it
		Queue<QItem> Q = new LinkedList<QItem>();
		Q.add(new QItem(root, 0)); // Horizontal distance of root is 0

		// Standard BFS or level order traversal loop
		while (!Q.isEmpty())
		{
			// Remove the front item and get its details
			QItem qi = Q.poll();
			int hd = qi.hd;
			TreeNode n = qi.node;

			// If this is the first node at its horizontal distance,
			// then this node is in top view
			if (!set.contains(hd))
			{
				set.add(hd);
				res.add(n.key);
			}

			// Enqueue left and right children of current node
			if (n.left != null)
				Q.add(new QItem(n.left, hd-1));
			if (n.right != null)
				Q.add(new QItem(n.right, hd+1));
		}

		return res;
	}
}

/*
Driver class to test above methods:
     1
    / \
   2   3
    \
     4
      \
       5
        \
         6
*/
public class Solution
{
	public static void main(String[] args)
	{
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.left.right.right = new TreeNode(5);
		root.left.right.right.right = new TreeNode(6);
		Tree t = new Tree(root);

		System.out.println(t.topView());
	}
}
