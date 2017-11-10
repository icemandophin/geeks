/*
hash map + catepillar approach:
grow j until substr cover target (cnt == 0)
then shift i until no longer covers (cnt != 0)
*/
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return null;
        }
        // create map of size 256 for ASC II
        int[] map = new int[256];
        String res = "";
        int n = s.length();
        // get # of chars in target
        int cnt = t.length();
        // cur min length
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        // add target string to map
        // records # of each char
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        while (j < n) {
            // if cur char exist in map/target, "cover" it and reduce target size
            if (map[s.charAt(j++)]-- > 0) {
                cnt--;
            }
            // cur substring covers target when cnt is 0
            while (cnt == 0) {
                if (j - i < min) {
                    // update res and min length
                    min = j - i;
                    res = s.substring(i, j);
                }
                // right shift i and shorten string
                if (map[s.charAt(i++)]++ == 0) {
                    cnt++;
                }
            }
        }
        return res;
    }
}

/*
hash map as template
*/
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || t.length() > s.length()) {
            return "";
        }
        String res = "";
        Map<Character, Integer> map = new HashMap<>();
        // load map with t
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int cnt = map.size();
        int n = s.length();
        int top = 0;
        int end = 0;
        // min need to be n + 1 to include s == t scenario
        int min = n + 1;
        // catepillar approach
        while (end < n) {
            // move end forward
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    cnt--;
                }
            }
            end++;
            // move top forward when t is fully matched
            while (cnt == 0) {
                // update cur result
                if (end - top < min) {
                    min = end - top;
                    res = s.substring(top, end);
                }

                char e = s.charAt(top);
                if (map.containsKey(e)) {
                    map.put(e, map.get(e) + 1);
                    if (map.get(e) > 0) {
                        cnt++;
                    }
                }
                top++;
            }
        }

        return res;
    }
}
