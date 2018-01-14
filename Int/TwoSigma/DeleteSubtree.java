/*
given a tree in array format, delete its specified subtree
*/
class TreeNode {
    public int val;
    public int parent;
    // indicate if node is deleted
    public boolean valid;
    public boolean visited;

    public TreeNode(int val, int parent, boolean valid, boolean visited) {
        this.val = val;
        this.parent = parent;
        this.valid = valid;
        this.visited = visited;
    }
}

public class Tree {
    private TreeNode[] tree;
    private int capacity;
    private int size;

    public Tree(TreeNode[] tree) {
        this.tree = tree;
        this.capacity = tree.length;
        this.size = tree.length;
    }

    public boolean deleteSubTree(int index) {
        // invalid input
        if (index < 0 || index >= capacity) {
            return false;
        }
        // has deleted
        if (!tree[index].valid) {
            return false;
        }
        // refresh flag
        for (int i = 0; i < capacity; i++) {
            tree[i].visited = false;
        }
        // delet target
        tree[index].valid = false;
        tree[index].visited = true;
        size--;
        // traverse and find target's children
        // delete chose children
        for (int i = 0; i < capacity; i++) {
            if (!tree[i].visited) {
                int parent = findVisited(i);
                // delete valid children
                if (parent != -1 && !tree[parent].valid) {
                    delete(i, parent);
                }
            }
        }

        return true;
    }
    // find unvisited node's ancester/ all parent to top
    private int findVisited(int index) {
        while (index != -1 && !tree[index].visited) {
            tree[index].visited = true;
            index = tree[index].parent;
        }

        return index;
    }
    // delete every node between index and parent
    private void delete(int index, int parent) {
        while (index != parent) {
            if (tree[index].valid) {
                // mark node as deleted
                tree[index].valid = false;
                size--;
            }

            index = tree[index].parent;
        }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1, -1, true, false);
        TreeNode b = new TreeNode(2, 3, true, false);
        TreeNode c = new TreeNode(3, 3, true, false);
        TreeNode d = new TreeNode(4, 2, true, false);
        TreeNode e = new TreeNode(5, 2, true, false);
        TreeNode f = new TreeNode(6, 1, true, false);
        TreeNode g = new TreeNode(7, 4, true, false);

        Tree tree = new Tree(new TreeNode[] { g, e, b, a, f, d, c });
        System.out.println(tree.size);
        tree.deleteSubTree(4);
        System.out.println(tree.size);

        System.out.println(tree.size);
        tree.deleteSubTree(2);
        System.out.println(tree.size);
    }
}
