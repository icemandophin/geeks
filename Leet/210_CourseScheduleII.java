/*
similar to #207 - add courses to path during DFS:
no loop detected => add cur course (child before parent)
then reverse order
*/
class Solution {
    public int[] findOrder(int n, int[][] pre) {
        // build array of size n
        // map[i] contains a list of courses that depend on i as prerequisite
        List<Integer>[] map = new List[n];
        for (int i = 0; i < n; ++i) {
            map[i] = new ArrayList<>();
        }
        List<Integer> path = new ArrayList<>();

        // build map for dependency
        for (int[] a : pre) {
            map[a[1]].add(a[0]);
        }
        // dfs each node and check if there exist parent depends on children
        for (int i = 0; i < n; ++i) {
            // mark visited course parent -> children
            boolean[] visit = new boolean[n];

            if (!dfs(map, i, visit, path)) {
                return new int[0];
            }
        }

        // no loop overall - convert path to res
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = path.get(n - i - 1);
        }

        return res;
    }

    private boolean dfs(List<Integer>[] map, int i, boolean[] visit, List<Integer> path) {
        if (visit[i]) {
            // found child is prerequisite of parent
            return false;
        }

        if (path.contains(i)) {
            // checked no loop in i's route
            return true;
        }

        // check if there is loop on course i's route
        visit[i] = true;
        // check dependent courses
        for (Integer child : map[i]) {
            if (!dfs(map, child, visit, path)) {
                return false;
            }
        }
        // no loop detected
        // save to add i
        visit[i] = false;
        path.add(i);

        return true;
    }
}
