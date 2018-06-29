// same as LC 291
/*
BackTracking + DFS:
for each pattern key p[i], try to match it for all substrings in str
if current match fail, backtrack and try next possibility
*/

/*
Given a pattern and a string input - find if the string follows the same
pattern and return 0 or 1.
Examples:
1) Pattern : "abab", input: "redblueredblue" should return 1.
2) Pattern: "aaaa", input: "asdasdasdasd" should return 1.
3) Pattern: "aabb", input: "xyzabcxzyabc" should return 0.
*/

public class Solution {
    // hash map new pattern to word
    private Map<Character, String> map = new HashMap<>();
    // record set of marked words
    private Set<String> set = new HashSet<>();
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern==null || str==null) {
            return false;
        }
        return dfs(pattern, 0, str, 0);
    }
    // DFS to find if match of pattern[i : END] and str[j : END] exist
    private boolean dfs(String pattern, int i, String str, int j) {
        // check length first
        if (i == pattern.length() && j == str.length()) {
            // got full match between pattern & str
            return true;
        }
        if (i == pattern.length() || j == str.length()) {
            // cur try is not full match
            return false;
        }
        // get cur key
        char key = pattern.charAt(i);
        // try to match key with word: str[j : cut) -> cut not included
        for (int cut = j + 1; cut <= str.length(); cut++) {
            // cut substring as word
            String word = str.substring(j, cut);
            // check if cur key or word has been matched before
            if (!map.containsKey(key) && !set.contains(word)) {
                // insert new mapping and continue
                map.put(key, word);
                set.add(word);
                if (dfs(pattern, i + 1, str, cut)) {
                    // remaining got matched, success
                    return true;
                } else {
                    // sub-match fail, back track and try another possibility
                    map.remove(key);
                    set.remove(word);
                }
            } else if (map.containsKey(key) && map.get(key).equals(word)) {
                // cur key - word fit prev mapping
                if (dfs(pattern, i + 1, str, cut)) {
                    // remaining got matched, success
                    return true;
                } else {
                    // remaining part do NOT match
                    // continue and try another possibility
                }
            } else {
                // cur key - word does NOT fit prev mapping
                // try another possibility
            }
        }
        // run out of all possibilities
        return false;
    }
}
