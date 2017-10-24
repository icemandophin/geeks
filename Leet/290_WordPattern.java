/*
Given a pattern and a string str, find if str follows the same pattern.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.

Notes:
Both pattern and str contains only lowercase alphabetical letters.
Both pattern and str do not have leading or trailing spaces.
Each word in str is separated by a single space.
Each letter in pattern must map to a word with length that is at least 1.
*/

/*
common spproach:
Hash Map for checking key-value mapping
Hash set for checking value existing mapping  
*/
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        // parse words per space
        String[] words = str.split(" ");
        int n = words.length;
        if (pattern.length() != n) {
            // pattern[] and words[] have same length
            return false;
        }
        // hash map new pattern to word
        Map<Character, String> map = new HashMap<>();
        // record set of marked words
        Set<String> set = new HashSet<>();
        // traverse each key in pattern
        for (int i = 0; i < n; ++i) {
            char ch = pattern.charAt(i);
            String word = words[i];
            if (map.containsKey(ch)) {
                // check if pattern mapping match prev
                if (map.get(ch).equals(word)) {
                    continue;
                } else {
                    return false;
                }
            } else {
                // check if word is matched before
                // then add new mapping
                if (set.contains(word)) {
                    // cur word has been mapped to another key => mismatch
                    return false;
                } else {
                    map.put(ch, word);
                    set.add(word);
                }
            }
        }
        return true;
    }
}
