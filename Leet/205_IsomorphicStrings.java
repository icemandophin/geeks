/*
core idea: ensure 1 to 1 mapping
Check one to many: one hashmap to record s[i] -> t[j]
Check many to one: one hashset to mark mapped items
*/
class Solution {
    public boolean isIsomorphic(String s, String t) {
        // quick check: diff len => cannot match
        if (s.length() != t.length()) {
            return false;
        }

        // record mapping of chars s -> t
        Map<Character, Character> map = new HashMap<>();
        // mark chars that has been mapped
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); ++i) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            // check and map s[i] and t[i]
            if (map.containsKey(sc)) {
                if (map.get(sc) != tc) {
                    // a is mapped to b & c
                    return false;
                }
            } else {
                if (set.contains(tc)) {
                    // cannot map a -> c
                    // since there exist b -> c
                    return false;
                }
                // map sc -> tc
                map.put(sc, tc);
                set.add(tc);
            }
        }

        return true;
    }
}

/*
optimize: map char to int[256]
*/
public boolean isIsomorphic (String s, String t) {
        // map char to its last appearance index
        // 0 means this char not exist in cur string
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // diff value => not match
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) {
                return false;
            }
            // map s.charAt(i) to t.charAt(i)
            // by assign same value
            // +1 to make val > 0
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }

        return true;
    }
