/*
union-find:
connect nodes by union their roots
for last edge that makes circle => its nodes makes same root
*/
class Solution {
    public int[] findRedundantConnection(int[][] e) {
        int n = e.length;
        int[] root = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            root[i] = i;
        }

        for (int[] a : e) {
            int p1 = find(root, a[0]);
            int p2 = find(root, a[1]);

            if (p1 == p2) {
                return a;
            }

            root[p1] = p2;
        }

        return null;
    }

    private int find(int[] root, int x) {
        if (x == root[x]) {
            return x;
        }

        return root[x] = find(root, root[x]);
    }
}

class Solution {
    public int[] findRedundantConnection(int[][] e) {
        int n = e.length;
        // record each node's neighbors
        List<Integer>[] map = new List[n + 1];

        for (int i = 1; i <= n; ++i) {
            map[i] = new ArrayList<>();
        }

        for (int[] a : e) {
            int i = a[0];
            int j = a[1];
            boolean[] visit = new boolean[n + 1];
            // check if i and j are connected
            if (dfs(map, visit, i, j)) {
                return a;
            }
            // update neighbors
            map[i].add(j);
            map[j].add(i);
        }

        return null;
    }

    private boolean dfs(List<Integer>[] map, boolean[] visit, int i, int j) {
        if (visit[i]) {
            return false;
        }

        if (i == j) {
            return true;
        }

        visit[i] = true;
        for (int x : map[i]) {
            // check if j is connected to i's neighbors
            if (dfs(map, visit, x, j)) {
                return true;
            }
        }

        return false;
    }
}
