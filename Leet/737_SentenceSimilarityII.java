/*
union-find approach:
1. build root mapping for each pair
2. find root (map.get(root) == root) for each word and compare
*/
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) {
            return false;
        }
        // map each word to its root word
        Map<String, String> map = new HashMap<>();
        for (String[] pair : pairs) {
            // init each root to itself
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], pair[0]);
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], pair[1]);
            }
            // union root of two words
            setParent(map, pair[0], pair[1]);
        }

        for (int i = 0; i < words1.length; i++) {
            String parent1 = getParent(map, words1[i]);
            String parent2 = getParent(map, words2[i]);
            if (!parent1.equals(parent2)) {
                return false;
            }
        }
        return true;
    }
    // union
    private void setParent(Map<String, String> map, String word1, String word2) {
        String parent1 = getParent(map, word1);
        String parent2 = getParent(map, word2);
        map.put(parent1, parent2);
    }
    // find
    private String getParent(Map<String, String> map, String word) {
        if (!map.containsKey(word)) {
            return word;
        }

        while (!word.equals(map.get(word))) {
            word = map.get(word);
        }
        
        return word;
    }
}
