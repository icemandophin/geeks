public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        if (bfs(beginWord, endWord, new HashSet<String>(wordList), map)) {
            List<String> curr = new ArrayList<>();
            curr.add(beginWord);

            dfs(beginWord, endWord, map, curr, result);
        }


        return result;
    }

    private boolean bfs(String beginWord, String endWord, Set<String> wordList, Map<String, List<String>> map) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordList.remove(beginWord);
        boolean isFound = false;

        while (!queue.isEmpty()) {
            int len = queue.size();
            Set<String> visited = new HashSet<>();

            for (int i = 0; i < len; i++) {
                String str = queue.poll();
                map.put(str, new ArrayList<String>());
                List<String> neighbors = getNeighbors(str);

                for (String neighbor : neighbors) {
                    if (wordList.contains(neighbor)) {
                        map.get(str).add(neighbor);

                        if (neighbor.equals(endWord)) {
                    	    isFound = true;
                    	}

                        if (!isFound && visited.add(neighbor)) {
                            queue.offer(neighbor);
                        }
                    }
                }
            }

            if (isFound) {
                break;
            }

            wordList.removeAll(visited);
        }

        return isFound;
    }

    private void dfs(String beginWord, String endWord, Map<String, List<String>> map, List<String> curr, List<List<String>> result) {
        if (beginWord.equals(endWord)) {
            result.add(new ArrayList<String>(curr));
            return;
        }

        if (!map.containsKey(beginWord)) {
            return;
        }

        for (String neighbor : map.get(beginWord)) {
            curr.add(neighbor);
            dfs(neighbor, endWord, map, curr, result);
            curr.remove(curr.size() - 1);
        }
    }

    private List<String> getNeighbors(String str) {
        List<String> neighbors = new ArrayList<>();

        for (int j = 0; j < str.length(); j++) {
            char[] chars = str.toCharArray();

            for (char c = 'a'; c <= 'z'; c++) {
                if (c != chars[j]) {
                    chars[j] = c;
                    String neighbor = new String(chars);;
                    neighbors.add(neighbor);
                }
            }
        }

        return neighbors;
    }
}
