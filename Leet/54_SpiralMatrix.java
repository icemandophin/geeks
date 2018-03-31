/*
define top, end, left, right boundary of traverse
each time traverse / populate one col / row:
1. update associated count
2. if there is collision (t/e and l/r) exit loop
*/
class Solution {
    public List<Integer> spiralOrder(int[][] a) {
        List<Integer> res = new ArrayList<>();

        if (a.length == 0 || a[0].length == 0) {
            return res;
        }
        
        int m = a.length;
        int n = a[0].length;

        // record cur boundary for traverse
        int l = 0;
        int r = n - 1;
        int t = 0;
        int e = m - 1;
        // traverse can end after any of 4 direction traverse
        // => check if boundary hit
        while (true) {
            // traverse l -> r
            for (int j = l; j <= r; ++j) {
                res.add(a[t][j]);
            }
            // update t count after traverse
            if (++t > e) {
                break;
            }
            // traverse t -> e
            for (int i = t; i <= e; ++i) {
                res.add(a[i][r]);
            }
            // update r count
            if (l > --r) {
                break;
            }
            // traverse r -> l
            for (int j = r; j >= l; --j) {
                res.add(a[e][j]);
            }
            if (t > --e) {
                break;
            }
            // traverse e -> t
            for (int i = e; i >= t; --i) {
                res.add(a[i][l]);
            }
            if (++l > r) {
                break;
            }
        }

        return res;
    }
}
