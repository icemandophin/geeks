/*
next jump depend on last jump len => for each stone:
1. map each stone's position(stones[i]) to all possible len of last jump
2. from each stone, generte all stones it can reach and update mapping
then check if last stone is accessible / has jump len to it
*/
class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }

        int n = stones.length;
        // for each stone, record all possible last jump length
        // to reach this stone
        Map<Integer, Set<Integer>> map = new HashMap<>();
        // init for each stone
        for (int i = 0; i < n; ++i) {
            map.put(stones[i], new HashSet<>());
        }
        map.get(0).add(0);

        for (int i = 0; i < n; ++i) {
            // k is the len of last jump to arrive stone[i]
            for (Integer k : map.get(stones[i])) {
                // j is possible len for next jump
                for (int j = k - 1; j <= k + 1; ++j) {
                    // can reach this stone with jump len j
                    int dest = stones[i] + j;
                    if (j > 0 && map.containsKey(dest)) {
                        map.get(dest).add(j);
                    }
                }
            }
        }

        // check last stone
        return !map.get(stones[n - 1]).isEmpty();

    }
}

/*
DFS approach:
*/
class Solution {
    public boolean canCross(int[] stones) {
        return dfs(stones, 0, 0, new boolean[stones.length][stones.length]);
    }

    private boolean dfs(int[] stones, int start, int k, boolean[][] visited) {
        if (start == stones.length - 1) {
            return true;
        }

        if (visited[start][k]) {
            return false;
        }

        visited[start][k] = true;

        for (int i = start + 1; i < stones.length; i++) {
            int diff = stones[i] - stones[start];

            if (diff < k - 1) {
                continue;
            } else if (diff <= k + 1) {
                if (dfs(stones, i, diff, visited)) {
                    return true;
                }
            } else {
                break;
            }
        }

        return false;
    }
}
