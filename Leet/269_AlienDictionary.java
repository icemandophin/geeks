/*
Topological Sort implemented by BFS:
time: O(n) space: O(n)
    use map1: map to store <c, set of char after c>
    use map2: degree to store <c, # of char before c>
    Iterate each two adjacent string, to fill map and update degree
    use a queue to do BFS:
    add c to queue when its degree is 0
    when remove a c, minus degree
Reference: https://segmentfault.com/a/1190000003795463
*/
import java.util.*;

public class Solution {
    public static void main(String[] args){
    	String[] input = { "wrt",
    	                   "wrf",
    	                   "er",
    	                   "ett",
    	                   "rftt" };
        System.out.println(alienOrder(input));
    }
    public static String alienOrder(String[] words) {
    	// map char c and set of chars after c according to words
        // it contains all chars that pointed by c
        HashMap<Character, Set<Character>> map = new HashMap<>();
        // map # of chars before char c in words as DEGREE
        // it counts how many chars pointing to c
        HashMap<Character, Integer> degree = new HashMap<>();
        StringBuilder res = new StringBuilder();
        // initialize degree map as pre-processing
        // otherwise will miss some chars that does not have degree
        for(int i = 0; i < words.length; ++i) {
            char[] word = words[i].toCharArray();
            for(int j = 0; j < word.length; ++j) {
                // degree size will also be total number of nodes/chars
                degree.put(word[j], 0);
            }
        }
        // compare adjacent string & fill map
        // every adjancent string pair contains edge info
        // eg: "abc" and "abd" => edge c->d
        for(int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int len = Math.min(cur.length(), next.length());
            for(int j = 0; j < len; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                // find 1st diff char between cur and next
                if(c1 != c2) {
                	// watch 'Set' declaration
                    Set<Character> set = new HashSet<Character>();
                    if(map.containsKey(c1)) {
                    	set = map.get(c1);
                    }
                    if(!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    // rest comparison is meaningless & not record it!
                    break;
                }else {
                    // edge case - no order: ["wrtkj","wrt"]
                	// 1:next stop at end
                	// 2: cur still have lefts
                    if(j + 1 == next.length() && j + 1 < cur.length()) {
                    	return "";
                    }
                }
            }
        }
        // BFS - use Queue to pop char in order
        Queue<Character> queue = new LinkedList<Character>();
        for(char c: degree.keySet()) {
            if(degree.get(c) == 0) {
            	// eg:[zx,zy], c: z,x
                queue.add(c);
            }
        }
        while(!queue.isEmpty()) {
            char cur = queue.remove();
            res.append(cur);
            if(map.containsKey(cur)) {
                for(char c: map.get(cur)) {
                    degree.put(c, degree.get(c) - 1);
                    if(degree.get(c) == 0) {
                    	//add next char
                    	queue.add(c);
                    }
                }
            }
        }
        // avoid loops. only < possible -- eg: ["qd","ab"] res = qa
        if(res.length() != degree.size()) {
        	return "";
        }

        return res.toString();
    }
}
