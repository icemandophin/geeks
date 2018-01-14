/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();

        if (graph == null || graph.size() == 0) {
            return result;
        }

        for (DirectedGraphNode node : graph) {
            helper(node, result);
        }

        Collections.reverse(result);

        return result;
    }

    private void helper(DirectedGraphNode node, ArrayList<DirectedGraphNode> result) {
        if (result.contains(node)) {
            return;
        }

        for (DirectedGraphNode neighbor : node.neighbors) {
            helper(neighbor, result);
        }

        result.add(node);
    }
}
