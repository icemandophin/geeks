import java.util.*;

public class Solution {
    public static void main(String[] args) {
    	// no dup
        String[] t = {"made", "in", "spain"};
        // dup possible
        String[] s = {"made", "weather", "forecast", "says", "that", "made", "rain", "in", "spain", "stays"};
        System.out.println(findMinWindowOfTags(s, t));
    }

    public static List<Integer> findMinWindowOfTags(String[] source, String[] target) {
    	List<Integer> res = new ArrayList<>();
    	Map<String, Integer> map = new HashMap<>();

    	for (String t : target) {
    		map.put(t, map.getOrDefault(t, 0) + 1);
    	}

    	int cnt = target.length;
    	int n = source.length;
    	int i = 0, j = 0;
    	int x = 0, y = n - 1;
    	int size = Integer.MAX_VALUE;

    	while (j < n) {
    		String cur = source[j];
    		map.put(cur, map.getOrDefault(cur, 0) - 1);
    		if (map.get(cur) == 0) {
    			cnt--;
    		}
    		j++;

    		while (cnt == 0) {
    			if (j - i < size) {
    				size = j - i;
    				x = i;
    				y = j - 1;
    			}

    			String left = source[i];
    			map.put(left, map.getOrDefault(left, 0) + 1);
        		if (map.get(left) == 1) {
        			cnt++;
        		}
    			i++;
    		}
    	}

    	res.add(x);
    	res.add(y);

    	return res;
    }
}
