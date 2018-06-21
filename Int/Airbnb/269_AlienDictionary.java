public class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        if (words.length == 1) return words[0];

        boolean[][] graph = new boolean[26][26];
        // mark existing letters
        for (String w : words) {
            for (char c : w.toCharArray()) {
                graph[c - 'a'][c - 'a'] = true;
            }
        }
        // build adjacent matrix
        int first = 0;
        int second = 1;
        while (second < words.length) {
            String s1 = words[first];
            String s2 = words[second];
            int minLen = Math.min(s1.length(), s2.length());
            for (int i = 0; i < minLen; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    graph[s1.charAt(i) - 'a'][s2.charAt(i) - 'a'] = true;
                    break;
                }
            }
            first++;
            second++;
        }

        // Do topologic sort
        StringBuilder sb = new StringBuilder();
        boolean[] path = new boolean[26];
        for (int i = 0; i < 26; i++) {
            if (!dfs(graph, sb, i, path)) return "";
        }

        for (int i = 0; i < 26; i++) {
            if (graph[i][i]) sb.append((char)(i + 'a'));
        }
        return sb.reverse().toString();
    }

    /** Do DFS to do topological sort. Return false for invalid input. */
    boolean dfs(boolean[][] graph, StringBuilder sb, int index, boolean[] path) {
        if (!graph[index][index]) return true; // visited or non-existing letter
        path[index] = true; // track letters in the dfs path for detecting if DAG or not
        for (int i = 0; i < 26; i++) {
            if (i == index || !graph[index][i]) continue;
            if (path[i]) return false; // cyclic path (non-DAG)
            if (!dfs(graph, sb, i, path)) return false;
        }
        path[index] = false;
        graph[index][index] = false;
        sb.append((char)(index + 'a'));
        return true;
    }
}
