/*
invalid when there is loop in course graph
=> detect loop:
dfs from root(no prerequesite needed),
if dependent/child course has been visited already => loop detected
*/
class Solution {
    public boolean canFinish(int n, int[][] pre) {
        // build array of size n
        // map[i] contains a list of courses that depend on i as prerequisite
        List<Integer>[] map = new List[n];
        for (int i = 0; i < n; ++i) {
            map[i] = new ArrayList<>();
        }
        // build map for dependency
        for (int[] a : pre) {
            map[a[1]].add(a[0]);
        }
        // dfs each node and check if there exist parent depends on children
        for (int i = 0; i < n; ++i) {
            // mark visited course parent -> children
            boolean[] visit = new boolean[n];

            if (!dfs(map, i, visit)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(List<Integer>[] map, int i, boolean[] visit) {
        if (visit[i]) {
            // found child is prerequisite of parent
            return false;
        }
        // check if there is loop on course i's route
        visit[i] = true;
        // check dependent courses
        for (Integer child : map[i]) {
            if (!dfs(map, child, visit)) {
                return false;
            }
        }
        // no loop detected - backtrack
        // avoid false visit mark when dfs another branch
        visit[i] = false;

        return true;
    }
}
