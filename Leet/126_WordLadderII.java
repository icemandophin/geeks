class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        // map of word in dict -> its trans words
        Map<String, List<String>> map = new HashMap<>();
        // set of words in dict
        Set<String> set = new HashSet<>(wordList);

        // check if path exist to reducce time complexity
        if (bfs(beginWord, endWord, set, map)) {
            // dfs to find shortest paths
            List<String> cur = new ArrayList<>();
            cur.add(beginWord);
            dfs(beginWord, endWord, map, cur, res);
        }

        return res;
    }
    // find shortest paths with trans word map
    private void dfs(String start, String end, Map<String, List<String>> map, List<String> cur, List<List<String>> res) {
        if (start.equals(end)) {
            // path found => add to res
            res.add(new ArrayList<String>(cur));

            return;
        }

        if (!map.containsKey(start)) {
            // cannot move to next word in dict
            return;
        }

        List<String> trans = map.get(start);
        for (String t : trans) {
            cur.add(t);
            // search from each trans word
            dfs(t, end, map, cur, res);
            // backtrack
            cur.remove(cur.size() - 1);
        }
    }

    // iterative bfs to check if there exist path start -> end
    // and build map of each word's neighbors
    private boolean bfs(String start, String end, Set<String> set, Map<String, List<String>> map) {
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        set.remove(start);
        boolean res = false;

        while (!q.isEmpty()) {
            int n = q.size();
            // record visited trans word for cur layer
            // then remove it from set as trim
            Set<String> visit = new HashSet<>();

            // bfs words in the same layer
            for (int i = 0; i < n; ++i) {
                String cur = q.poll();
                // build map of trans word
                map.put(cur, new ArrayList<String>());
                List<String> trans = transform(cur);
                // check and enqueue trans word
                for (String t : trans) {
                    // check if exist in dict
                    if (set.contains(t)) {
                        map.get(cur).add(t);
                        // match target
                        if (t.equals(end)) {
                            res = true;
                        }
                        // enqueue if t haven't been bfs before
                        if (!res && !visit.contains(t)) {
                            q.offer(t);
                            visit.add(t);
                        }
                    }
                }
            }

            // map should contain info for shortest path
            if (res) {
                return res;
            }
            // remove visited words from set to avoid duplicate search
            set.removeAll(visit);
        }

        return res;
    }

    private List<String> transform(String str) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < str.length(); ++i) {
            char[] ch = str.toCharArray();
            for (char j = 'a'; j <= 'z'; ++j) {
                if (ch[i] != j) {
                    ch[i] = j;
                    res.add(new String(ch));
                }
            }
        }

        return res;
    }
}
