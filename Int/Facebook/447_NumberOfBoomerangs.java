/*
for point A(i, j) if there is k points that share same distance to A
then we can build A(k, 2) = k*(k - 1) boomerangs
since x, y in [-10000,10000] => x*x + y*y in int range
traverse points and save distance square into hash map:
distance square => number it appears
*/
class Solution {
    public int numberOfBoomerangs(int[][] p) {
        if (p == null) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < p.length; ++i) {
            // build hash map for cur point
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < p.length; ++j) {
                // skip itself
                if (i == j) {
                    continue;
                }
                // get distance between i, j
                int dis = distance(p[i], p[j]);
                // update this count
                int cnt = map.getOrDefault(dis, 0);
                map.put(dis, cnt + 1);
            }
            // for count k add to res
            for (int k : map.values()) {
                res += k * (k - 1);
            }
        }

        return res;
    }
    public int distance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }
}
