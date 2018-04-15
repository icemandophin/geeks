/*
union - find approach:
keep root[n] for each node's root node
*/
public class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] root = new int[n];
        // there are n separate nodes at start
        int count = n;
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        for (int[] edge : edges) {
            int parent1 = find(root, edge[0]);
            int parent2 = find(root, edge[1]);
            // when edge connects 2 separate components
            // decrease count by 1
            if (parent1 != parent2) {
                count--;
                root[parent1] = parent2;
            }
        }

        return count;
    }

    private int find(int[] root, int child) {
        int parent = child;

        while (parent != root[parent]) {
            parent = root[parent];
        }

        root[child] = parent;

        return parent;
    }
}

/*
DFS approach: similiar to number of islands
*/
public class Solution {
    public int countComponents(int n, int[][] edges) {
        // list[i] contains all nodes that connects to i
        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(list, i, visited);
                count++;
            }
        }

        return count;
    }

    private void dfs(List<List<Integer>> list, int i, boolean[] visited) {
        if (visited[i]) {
            return;
        }

        visited[i] = true;

        for (int j : list.get(i)) {
            dfs(list, j, visited);
        }
    }
}
