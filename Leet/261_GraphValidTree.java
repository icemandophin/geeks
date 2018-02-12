/*
union-find:
if there is loop
=> two nodes in loop will connect in find() before found in edges[][]
*/
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // n nodes require n - 1 edges to connect without loop
        if (edges.length != n - 1) {
            return false;
        }
        // record root of each node
        int[] rec = new int[n];
        for (int i = 0; i < n; i++) {
            rec[i] = i;
        }

        for (int[] edge : edges) {
            int parent1 = find(rec, edge[0]);
            int parent2 = find(rec, edge[1]);
            // check before union
            // e[1] & e[2] already connect
            // => there exist loop in e[1], e[2] and common root
            if (parent1 == parent2) {
                return false;
            } else {
                // connect e[1] and e[2]
                rec[parent1] = parent2;
            }
        }

        return true;
    }

    int find(int[] rec, int child) {
        int parent = child;

        while (parent != rec[parent]) {
            parent = rec[parent];
        }

        rec[child] = parent;

        return parent;
    }
}

/*
DFS:
build map (node -> connected nodes)
=> dfs each node and its connection
if there exist loop => one connected node is already visited during DFS
*/
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        // for each node, build map of connected nodes
        List<List<Integer>> rec = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            rec.add(new ArrayList<Integer>());
        }
        // undirected map => add a - b and b - a
        for (int[] edge : edges) {
            rec.get(edge[0]).add(edge[1]);
            rec.get(edge[1]).add(edge[0]);
        }
        // dfs and mark visited
        boolean[] visited = new boolean[n];
        dfs(rec, 0, -1, visited);
        // record pre-node during DFS
        // to avoid looping back to pre-node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> rec, int i, int parent, boolean[] visited) {
        // return if node is visited/invalid
        if (visited[i]) {
            return false;
        }
        // mark cur node
        visited[i] = true;
        // when dfs connection, skip pre/parent node
        // if next node already visited twice => loop
        for (Integer j : rec.get(i)) {
            if (j != parent && !dfs(rec, j, i, visited)) {
                return false;
            }
        }

        return true;
    }
}
