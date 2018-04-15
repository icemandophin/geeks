package Solution;

import java.util.*;

/*
 * Given a list of strings, return a list of lists where each sublist is an isomorphic group.
 *
 * ["mark", "bark", "lark", "marr", "blue", "abc", "def", "foo"] =>
 * [["mark", "bark", "lark", "blue"], ["marr"], ["abc", "def"], ["foo"]]
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println("Hello Stargazer!");
        List<String> input = new ArrayList<>();
        input.add("mark");
        input.add("bark");
        input.add("lark");
        input.add("marr");
        input.add("blue");
        input.add("abc");
        input.add("def");
        input.add("foo");

        List<List<String>> res = groupIsomorphic(input);
        for (List<String> group : res) {
            for (String s : group) {
                System.out.println(s);
            }
            // group separator
            System.out.println("*******");
        }
    }
    // if a->b and b->c then a->c
    // => use one word as key and build list of isomorphic
    private static List<List<String>> groupIsomorphic(List<String> strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            if (!addToExistGroup(map, str)) {
                // no matching isomorphic
                // use str as key of new group
                List<String> val = new ArrayList<>();
                // add key to its own group
                val.add(str);
                map.put(str, val);
            }
        }

        List<List<String>> res = new ArrayList<>(map.values());

        return res;
    }
    // try to add new word to existing isomorphic group
    // return false if cannot find a match
    private static boolean addToExistGroup(Map<String, List<String>> map, String str) {
        for (String key : map.keySet()) {
            if (isIsomorphic(str, key)) {
                map.get(key).add(str);

                return true;
            }
        }

        return false;
    }
    // check if s and t are isomorphic
    private static boolean isIsomorphic(String s, String t) {
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
