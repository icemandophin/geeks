/*
union-find approach
*/
class Solution {
    // record cur number of unions
    private int cnt = 0;

    public int findCircleNum(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int n = m.length;
        int[] root = new int[n];
        int i, j;
        int res = n;
        // init as n unions
        for (i = 0; i < n; ++i) {
            root[i] = i;
        }
        for (i = 0; i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (m[i][j] == 1) {
                    // find root of i, j
                    int ri = find(root, i);
                    int rj = find(root, j);
                    // merge their union if i, j have diff root
                    if (ri != rj) {
                        root[ri] = rj;
                        // size decrease since two union become one
                        res--;
                    }
                }
            }
        }
        return res;
    }
    // compress and find root of x
    int find(int[] root, int x) {
        while (x != root[x]) {
            // path compression
            root[x] = root[root[x]];
            // jump to x's cur root
            x = root[x];
        }
        return x;
    }
}

/*
DFS approach
*/
public class Solution {
    public int findCircleNum(int[][] M) {
        int len = M.length;
        boolean[] visited = new boolean[len];
        int count = 0;

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                dfs(M, i, visited);
                count++;
            }
        }

        return count;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        visited[i] = true;

        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                dfs(M, j, visited);
            }
        }
    }
}
