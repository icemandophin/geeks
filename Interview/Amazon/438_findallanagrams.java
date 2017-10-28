package Amazon;
import java.util.*;

/*
sliding window + hash map:
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) {
            // return empty list
            return res;
        }
        // build hash map for char in p
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); ++i) {
            char ch = p.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        // get size of hash map / unique char in p
        int cnt = map.size();
        // left & right boundary of sliding window
        int top = 0;
        int end = 0;
        // traverse str s
        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                // decrease # of c
                map.put(c, map.get(c) - 1);
                // if all of c in p matched, shrink cnt
                if (map.get(c) == 0) {
                    cnt--;
                }
            }
            end++;

            while (cnt == 0) {
                // check if [top : end] is anagram
                if (end - top == p.length()) {
                    res.add(top);
                }
                // cur substring contains extra char that breaks anagram
                // move top forward
                char d = s.charAt(top);
                if (map.containsKey(d)) {
                    // take d out of cur substr, need increase in map
                    map.put(d, map.get(d) + 1);
                    if (map.get(d) > 0) {
                        cnt++;
                    }
                }
                top++;
            }
        }
        return res;
    }
}
