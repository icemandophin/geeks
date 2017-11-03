package Amazon;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
      String[][] input = new String[][] {
          {"friend1", "friend2"},
          {"friend3", "friend4"},
          {"friend4", "friend5"},
      };
      String[] str = new Solution().findLargestFriendCircle(input);
      input = new String[][] {
          {"friend1", "friend2"},
          {"friend3", "friend4"},
      };
      str = new Solution().findLargestFriendCircle(input);
    	input = new String[][] {
          {"friend1", "friend2"},
          {"friend3", "friend4"},
          {"friend1", "friend3"},
          {"friend4", "friend5"},
      };
      str = new Solution().findLargestFriendCircle(input);
    }
    // main method
    public String[] findLargestFriendCircle(String[][] str) {
    	// map each person to root person of its union
        Map<String, String> root = new HashMap<>();
        // map how many unions has key as root
        Map<String, Integer> map = new HashMap<>();
        // init each person as its own circle
        for(String [] pair : str) {
            root.put(pair[0], pair[0]);
            root.put(pair[1], pair[1]);
        }
        // traverse friendship and union circle
        for(String[] pair: str) {
        	String a = pair[0];
        	String b = pair[1];
        	// do NOT need to call find() since the root is not completed yet
        	String ra = root.get(a);
        	String rb = root.get(b);
        	// compare and union to lexicographic order smaller one
        	if (ra.compareTo(rb) < 0) {
        		root.put(rb, ra);
        	} else {
        		root.put(ra, rb);
        	}
        }
        /************************************************************/
        // critical: get final root for each person
        // need this to ensure all people are mapped to "real" root
        for(Map.Entry<String, String> ent : root.entrySet()) {
        	String curVal = ent.getValue();
        	String curKey = ent.getKey();
        	if (root.containsKey(curVal)) {
        		String finalRoot = find(root, curVal);
        		root.put(curKey, finalRoot);
        	}
        }
        /************************************************************/
        // generate root counting map
        for(Map.Entry<String, String> ent : root.entrySet()) {
        	String cur = ent.getValue();
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        // traverse map and get root with max len
        int maxLen = -1;
        String maxKey = null;
        for(Map.Entry<String, Integer> ent : map.entrySet()) {
        	int curVal = ent.getValue();
        	String curKey = ent.getKey();
            if(curVal > maxLen || (curVal == maxLen && curKey.compareTo(maxKey) < 0)) {
                maxLen = curVal;
                maxKey = curKey;
            }
        }
        //
        String[] res = new String[maxLen];
        for(Map.Entry<String, String> ent : root.entrySet()) {
            if(ent.getValue().equals(maxKey)) {
                res[--maxLen] = ent.getKey();
            }
        }
        Arrays.sort(res);
        System.out.println(Arrays.toString(res));

        return res;
    }
    // find root of person x
    public String find(Map<String, String> root, String x) {
    	while (x != root.get(x)) {
    		String cur = root.get(x);
    		root.put(x, root.get(cur));
    		x = root.get(root.get(x));
    	}
    	return x;
    }
}
