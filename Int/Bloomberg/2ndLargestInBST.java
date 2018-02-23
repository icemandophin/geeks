import java.util.*;
import javax.activation.MailcapCommandMap;

public class Solution {
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

		System.out.println(find2ndLargest(tree.getRoot()));
	}

    /*
     *  find 2nd largest in BST
     */
    public static int find2ndLargest(Node root) {
    	int res = Integer.MIN_VALUE;
    	Node pre = null;
    	Node cur = root;
    	// find right-most leaf
    	while (cur.right != null) {
    		pre = cur;
    		cur = cur.right;
    	}

    	if (cur.left != null) {
    		// find right-most node in left subtree
    		// which is 2nd largest
    		cur = cur.left;
    		while (cur.right != null) {
    			cur = cur.right;
    		}
    		res = cur.data;
    	} else {
    		// if no left child => pre/parent is 2nd largest
    		if (cur == root && pre == null) {
    			// single node
    			res = Integer.MIN_VALUE;
    		} else {
    			res = pre.data;
    		}
    	}

    	return res;
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}

class BinarySearchTree {
    // Root of BST
    Node root;
    // Constructor
    public BinarySearchTree()
    {
        root = null;
    }
    // get root of BST
    public Node getRoot() {
    	return root;
    }
    // function to insert new nodes
    public void insert(int data)
    {
        this.root = this.insertRec(this.root, data);
    }
    /* A utility function to insert a new node with given
    key in BST */
    Node insertRec(Node node, int data)
    {
        /* If the tree is empty, return a new node */
        if (node == null) {
            this.root = new Node(data);
            return this.root;
        }
        /* Otherwise, recur down the tree */
        if (data < node.data) {
            node.left = this.insertRec(node.left, data);
        } else {
            node.right = this.insertRec(node.right, data);
        }

        return node;
    }
    // class that stores the value of count
    public class count {
        int c = 0;
    }
}
