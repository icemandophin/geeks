/*
union-find:
init f[a] = a and cnt = n
m[a][b] == m[b][a] => only traverse b > a
if m[a][b] set => union a, b and update cnt/res
*/
class Solution {
    public int findCircleNum(int[][] m) {
        int n = m.length;
        int cnt = n;
        int[] f = new int[n];
        // init root
        for (int i = 0; i < n; ++i) {
            f[i] = i;
        }
        // search and union
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (m[i][j] == 1) {
                    cnt = union(f, i, j, cnt);
                }
            }
        }

        return cnt;
    }
    // union circle and return updated circle number
    // always let smaller index be root
    private int union(int[] f, int i, int j, int cnt) {
        // get roots
        int fa = find(f, i);
        int fb = find(f, j);
        if (fa != fb) {
            f[fb] = fa;
            --cnt;
        }

        return cnt;
    }
    // find root of person
    private int find(int[] f, int i) {
        if (f[i] == i) {
            return i;
        }

        return f[i] = find(f, f[i]);
    }
}

/*
DFS: for each new person not visited
1. add cnt as new circle
2. dfs all connected nodes and mark them visited
*/
class Solution {
    public int findCircleNum(int[][] m) {
        int n = m.length;
        boolean[] v = new boolean[n];
        int cnt = 0;
        // dfs for each person
        for (int i = 0; i < n; ++i) {
            if (v[i] == false) {
                v[i] = true;
                cnt++;
                dfs(m, v, i);
            }
        }

        return cnt;
    }
    // mark all related person of i visited
    private void dfs(int[][] m, boolean[] v, int x) {
        for (int i = 0; i < v.length; ++i) {
            if (m[x][i] == 1 && !v[i]) {
                v[i] = true;
                dfs(m, v, i);
            }
        }
    }
}

/*
BFS - iterative:
*/
public class Solution {
    public int findCircleNum(int[][] M) {
        int len = M.length;
        boolean[] visited = new boolean[len];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                queue.offer(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int k = queue.poll();

                    for (int j = 0; j < len; j++) {
                        if (M[k][j] == 1 && !visited[j]) {
                            queue.add(j);
                            visited[j] = true;
                        }
                    }
                }

                count++;
            }
        }

        return count;
    }
}
