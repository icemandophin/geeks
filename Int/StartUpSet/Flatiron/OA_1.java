// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int res = -1;
        
        for (int i = 0; i < n; ++i) {
            if (!map.containsKey(a[i])) {
                map.put(a[i], new ArrayList<>());
            }
            map.get(a[i]).add(i);
        }
        
        Arrays.sort(a);
        
        for (int i = 0; i < n; ++i) {
            int j = i;
            while(j < n && a[j] == a[i]) {
                j++;
            }
            List<Integer> lx = map.get(a[i]);
            List<Integer> ly = map.get(a[j]);
            int x = lx.get(lx.size() - 1);
            int y = ly.get(ly.size() - 1);
            int dis = Math.abs(x - y);
            res = Math.max(res, dis);
            i = j;
        }
        
        return res;
    }
}