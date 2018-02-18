/*
DFS approach:
build hashmap for clone -> origin
to avoid repeat creation
*/
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // map cloned node to origin node
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        return dfs(node, map);
    }
    // clone root and its neighbors
    // if node has been created before => direct return
    private UndirectedGraphNode dfs(UndirectedGraphNode root, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (root == null) {
            return null;
        }
        // return if node has been cloned before
        if (map.containsKey(root)) {
            return map.get(root);
        }
        // clone root
        UndirectedGraphNode clone = new UndirectedGraphNode(root.label);
        map.put(root, clone);
        // add cloned connection
        for (UndirectedGraphNode i : root.neighbors) {
            clone.neighbors.add(dfs(i, map));
        }

        return clone;
    }
}

/*
BFS with queue:
always enqueue node just cloned and handle its neighbors in BFS
*/
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        map.put(node, new UndirectedGraphNode(node.label));
        q.offer(node);

        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();

            for (UndirectedGraphNode i : cur.neighbors) {
                if (!map.containsKey(i)) {
                    map.put(i, new UndirectedGraphNode(i.label));
                    q.offer(i);
                }
                // connect cur node with cloned i
                map.get(cur).neighbors.add(map.get(i));
            }
        }

        return map.get(node);
    }
}

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
