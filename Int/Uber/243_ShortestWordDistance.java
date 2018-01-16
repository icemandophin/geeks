/*
O(n) approach:
traverse and update min distance when either word is found
*/
public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        // mark not found with -1
        int idx1 = -1;
        int idx2 = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                idx1 = i;
            } else if (words[i].equals(word2)) {
                idx2 = i;
            }
            // if both words have been found
            // update min distance
            if (idx1 != -1 && idx2 != -1) {
                min = Math.min(min, Math.abs(idx1 - idx2));
            }
        }

        return min;
    }
}
