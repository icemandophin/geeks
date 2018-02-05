/*
similiar to number of islands
countCluster() recursively count number of color clusters in the tree
dfs() mark all connected nodes with same color as visited
*/
package Amazon;
import java.util.*;

class Node {
    char color;
    Node left;
    Node right;
}

public class Solution {
    Set<Node> visited = new HashSet<>();
    public int countCluster(Node root, Set<Node> visited) {
        if (root == null) {
            return 0;
        }

        int cnt = 0;

        // cluster starts with parent-child pair with same color
        // which have not been visited before
        boolean cl = root.left != null && !visited.contains(root.left) &&
        !visited.contains(root) && root.left.color == root.color;
        boolean cr = root.right != null && !visited.contains(root.right) &&
        !visited.contains(root) && root.right.color == root.color;

        if (cl || cr) {
            // mark connected nodes in the same cluster as visited
            if (cl) {
                visited.add(root.left);
            }
            if (cr) {
                visited.add(root.right);
            }
            // add count once
            ++cnt;
        }

        visited.add(root);
        // recursively check subtree
        cnt += countCluster(root.left, visited);
        cnt += countCluster(root.right, visited);

        return cnt;
    }
}
