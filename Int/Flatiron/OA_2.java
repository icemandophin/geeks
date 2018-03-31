// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int[] solution(int[] a) {
        int n = a.length;
        int cap = 0;
        int[] res = new int[n - 1];

        for (int i = 0; i < n; ++i) {
            if (i == a[i]) {
                cap = i;
            } 
        }
        
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i != cap) {
                int dis = 1;
                int j = i;
                while (a[j] != cap) {
                    dis++;
                    j = a[j];
                }
                d[i] = dis;
            }
        }
        
        for (int i = 0; i < n; ++i) {
            if (d[i] > 0) {
                res[d[i] - 1]++;
            }
        }
        
        return res;
    }
}