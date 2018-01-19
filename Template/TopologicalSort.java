/*
List/Array version:
*/
class Node {
	int val;
	String id;
	String pid;
	public Node(String x, String y) {
		pid = x;
		id = y;
	}
}
/*
 take List of Nodes as input
 output List of ID will satisfy the relative orders
 build map of parent - children
 for each id in array:
 1. if visited already => skip
 2. if not visited and not map key => leaf or single root => add to res
 3. if not visited and is key => DFS each child and add to res
 */
class TopologicalSort {
	public static List<String> topoSort(List<Node> nodes, String[] ids) {
		List<String> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		Set<String> visited = new HashSet<>();

		// build order map
		for (Node node : nodes) {
			if (!map.containsKey(node.pid)) {
				map.put(node.pid, new ArrayList<String>());
			}
			map.get(node.pid).add(node.id);
		}
		// DFS for each id in array
		// elements in res in reverse (child-parent) order
		for (int i = 0; i < ids.length; ++i) {
			dfs(map, visited, ids[i], res);
		}
		// reverse order so that parent before children
		Collections.reverse(res);

		return res;
	}
	// DFS each node to ensure child is always added to res before parent
	private static void dfs(Map<String, List<String>> map, Set<String> visited, String cur, List<String> res) {
		if (visited.contains(cur)) {
			return;
		}

		// need to set cur visited first to void repeated search
		visited.add(cur);

		if (map.containsKey(cur)) {
			for (String child : map.get(cur)) {
				dfs(map, visited, child, res);
			}
		}

		res.add(cur);
	}
}

public class Solution {
	public static void main(String[] args) {
		String[] ids = {"a", "c", "e", "z", "f", "b", "d", "g", "k", "s"};
		List<Node> input = new ArrayList<Node>();
		// for root node pid is "*"
		input.add(new Node("a", "b"));
		input.add(new Node("a", "c"));
		input.add(new Node("a", "s"));
		input.add(new Node("b", "e"));
		input.add(new Node("c", "d"));
		input.add(new Node("k", "g"));
		input.add(new Node("*", "f"));
		input.add(new Node("d", "z"));

		System.out.println("Sorted: " + TopologicalSort.topoSort(input, ids));

	}
}


/*
Graphic Version:
*/
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
