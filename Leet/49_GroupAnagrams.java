/*
detect anagrams of the same word/group:
build hashmap using each word's lexicographic sorted anagram as key
build result list from hash map
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = lexicographicSort(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }

            map.get(key).add(str);
        }

        // construct List from hash map values
        return new ArrayList<>(map.values());
    }

    // given string return its anagram in lexicographic order
    private String lexicographicSort(String str) {
        char[] ch = str.toCharArray();
        Arrays.sort(ch);

        return new String(ch);
    }
}
