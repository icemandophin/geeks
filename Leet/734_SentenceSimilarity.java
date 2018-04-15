/*
build mapping for each word and its similiar words
*/
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        // length pre-check
        if (words1 == null || words2 == null || words1.length != words2.length) {
            return false;
        }

        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            String s1 = pairs[i][0];
            String s2 = pairs[i][1];
            // only need to insert once
            map.putIfAbsent(s1, new HashSet<String>());
            map.get(s1).add(s2);
        }
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2)) {
                continue;
            }
            // using either word as key 
            if (!map.containsKey(w1) || !map.get(w1).contains(w2)) {
                return false;
            }
        }

        return true;
    }
}
