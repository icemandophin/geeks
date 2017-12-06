/*
eg:
str = "democracy"
k = 5
res: "cracy", "ocrac"
*/

// brutal approach:
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String s = "wawaglknagagwunagkwkwagl";
        int k = 4;
        System.out.println(findSubstringWithOneRepeatChar(s, k));
    }

    public static List<String> findSubstringWithOneRepeatChar(String str, int k) {
    	List<String> res = new ArrayList<>();

    	for (int i = 0; i <= str.length() - k; ++i) {
    		String cur = str.substring(i, i + k);
    		if (checkSingleRepeat(cur)) {
    			res.add(cur);
    		}
    	}

        return res;
    }

    public static boolean checkSingleRepeat(String str) {
    	Map<Character, Integer> map = new HashMap<>();
    	boolean flag = false;
    	int n = str.length();

    	for (int i = 0; i < n; ++i) {
    		map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
    	}

    	for (Integer val : map.values()) {
    		if (val > 2) {
    			return false;
    		} else if (val == 2) {
    			if (flag) {
    				return false;
    			} else {
    				flag = true;
    			}
    		} else {
    			continue;
    		}
    	}

    	if (flag) {
    		return true;
    	} else {
    		return false;
    	}

    }
}
