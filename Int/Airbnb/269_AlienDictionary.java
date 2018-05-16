/*
Topological Sort DFS:
time: O(n) space: O(n)
*/
import java.util.*;

public class Solution {
    public String alienOrder(String[] words) {
        // each list in array records following chars of cur ch
        List<Integer>[] rec = new List[26];
        String prev = null;

        for (int i = 0; i < words.length; i++) {
            String curr = words[i];

            for (int j = 0; j < curr.length(); j++) {
                int idx = curr.charAt(j) - 'a';
                // init array for each char appeared
                if (rec[idx] == null) {
                    rec[idx] = new ArrayList<>();
                }
            }

            if (prev != null) {
                int len1 = prev.length();
                int len2 = curr.length();
                int j = 0;

                while (j < len1 && j < len2) {
                    int idx1 = prev.charAt(j) - 'a';
                    int idx2 = curr.charAt(j) - 'a';
                    // get relative order from 1st diff
                    if (idx1 != idx2) {
                        rec[idx1].add(idx2);
                        // cannot get further ordering from
                        // following chars
                        break;
                    }

                    j++;
                }

                if ((j == len1 || j == len2) && len1 > len2) {
                    // edge case: ["abcde", "abc"] => no order
                    return "";
                }
            }

            prev = curr;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            if (rec[i] == null || !dfs(rec, i, new boolean[26], sb)) {
                return "";
            }
        }

        sb.reverse();

        return sb.toString();
    }
    // check Topological order and add each char's children into res
    // before adding char itself
    private boolean dfs(List<Integer>[] rec, int i, boolean[] visited, StringBuilder sb) {
        if (visited[i]) {
            // loop detected
            return false;
        }

        String ch = Character.toString((char) ('a' + i));

        if (sb.indexOf(ch) != -1) {
            // i has been added to res by other ordering path
            return true;
        }

        visited[i] = true;

        for (Integer j : rec[i]) {
            if (!dfs(rec, j, visited, sb)) {
                return false;
            }
        }

        visited[i] = false;
        sb.append(ch);

        return true;
    }
}
