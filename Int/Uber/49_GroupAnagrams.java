class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // build map of sorted - original
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = sort(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            // pass by ref, map.put no needed
            map.get(key).add(str);
        }

        // return list based on map values
        return new ArrayList<>(map.values());
    }

    private String sort(String str) {
        if (str == null) {
            return null;
        }
        // convert to array to sort
        // then convert vack to str
        char[] ch = str.toCharArray();
        Arrays.sort(ch);
        String res = new String(ch);

        return res;
    }
}
