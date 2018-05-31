// This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.
//                      root
//                     / \
//                    a   b
//                   /\  / \
//                  c d e  f
//                 /
//                g
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Node a = new Node();
        Node b = new Node();
        Node root = new Node(a, b);
        Node c = new Node();
        Node d = new Node();
        Node e = new Node();
        Node f = new Node();
        Node g = new Node();
        a.left = c;
        a.right = d;
        b.left = e;
        b.right = f;
        c.left = g;

        System.out.println("Rsult should be: True Actual Result: " + checkAncester(root, a, g));
        System.out.println("Rsult should be: True Actual Result: " + checkAncester(root, root, f));
        System.out.println("Rsult should be: False Actual Result: " + checkAncester(root, b, g));
        System.out.println("Rsult should be: False Actual Result: " + checkAncester(root, d, g));
        System.out.println("Rsult should be: False Actual Result: " + checkAncester(root, null, c));
        System.out.println("Rsult should be: False Actual Result: " + checkAncester(null, b, c));
        System.out.println("Rsult should be: False Actual Result: " + checkAncester(root, g, f));
        System.out.println("Rsult should be: False Actual Result: " + checkAncester(root, g, g));
    }

    public static boolean checkAncester(Node root, Node a, Node b) {
        if (root == null || a == null || b == null || a == b) {
            return false;
        }

        dfs(root);

        Node cur = b;
        while(cur != null) {
            if (cur == a) {
                return true;
            }
            cur = cur.parent;
        }

        return false;
    }

    private static void dfs(Node root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            root.left.parent = root;
            dfs(root.left);
        }

        if (root.right != null) {
            root.right.parent = root;
            dfs(root.right);
        }

        return;
    }
}

class Node {
    public Node left;
    public Node right;
    public Node parent;

    public Node() {}

    public Node(Node l, Node r) {
        left = l;
        right = r;
    }
}
