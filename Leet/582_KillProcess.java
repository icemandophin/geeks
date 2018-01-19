/*
Iterative approach:
1. build map of each process and its children
2. BFS with queue: each time dequeue and kill target -> enqueue its children
*/
public class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        // build map
        for (int i = 0; i < ppid.size(); i++) {
            int parent = ppid.get(i);
            int child = pid.get(i);
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<Integer>());
            }

            map.get(parent).add(child);
        }
        // BFS to kill process and children
        q.offer(kill);
        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            for (Integer i : map.get(cur)) {
                q.offer(i);
            }
        }

        return res;
    }
}

/*
DFS approach:
1. build parent - children set map
2. for each child of root, dfs to delete tree
*/
public class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < ppid.size(); i++) {
            int parent = ppid.get(i);
            int child = pid.get(i);
            map.putIfAbsent(parent, new HashSet<>());
            map.get(parent).add(child);
        }

        dfs(map, kill, res);
        return res;
    }

    private void dfs(Map<Integer, Set<Integer>> map, int id, List<Integer> res) {
        res.add(id);

        if (!map.containsKey(id)) {
            return;
        }

        for (Integer child : map.get(id)) {
            dfs(map, child, res);
        }
    }
}
