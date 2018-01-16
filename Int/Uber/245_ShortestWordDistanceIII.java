/*
add same word compare from #243
if word1 == word2 => idx2 records latest index, idx1 records prev index
else idx1, idx2 records latest index of word1/word2
*/
public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int idx1 = -1;
        int idx2 = -1;
        boolean isSame = word1.equals(word2);
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (isSame) {
                    // update cur & prev index of word
                    idx1 = idx2;
                    idx2 = i;
                } else {
                    idx1 = i;
                }
            } else if (words[i].equals(word2)) {
                idx2 = i;
            }

            if (idx1 != -1 && idx2 != -1) {
                min = Math.min(min, Math.abs(idx1 - idx2));
            }
        }

        return min;
    }
}
