/*
Scan each row of matrix and convert problem to sub-problems of Largest Rectangle in Histogram
*/
class Solution {
    public int maximalRectangle(char[][] map) {
        int res = 0;
        // handle corner case
        if (map == null || map.length == 0 || map[0].length == 0) {
            return res;
        }
        int M = map.length;
        int N = map[0].length;
        // h[j] stores # of consecutive 1's of row j so far
        int[] h = new int[N];
        int i, j;
        //init first row for h[N] and res
        for (i = 0; i < N; ++i) {
            h[i] = (map[0][i] == '1') ? 1 : 0;
        }
        res = largestRectangleArea(h);
        // scan each row => update h[] => calculate current max rectangle based on h[]
        for (i = 1; i < M; ++i) {
            // update h[] for current row:
            // if map[i][j] =='1' h[j] +=1, else h[j] = 0
            for (j = 0; j < N; ++j) {
                h[j] = (map[i][j] == '1') ? h[j]+1 : 0;
            }
            // calculate max rectangle area so far and update res
            res = Math.max(res, largestRectangleArea(h));
        }
        return res;
    }
    // same method thta resolve #84 Largest Rectangle in Histogram
