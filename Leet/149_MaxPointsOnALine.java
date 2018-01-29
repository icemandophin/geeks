
public class Solution {
    public int maxPoints(Point[] points) {
        int len = points.length;
        int res = 0;

        for (int i = 0; i < len; i++) {
            // num of same points like points[i]
            int same = 1;
            // max num of points that in the same line with points[i]
            int max = 0;
            // build map of ("dx dy", num of points in this line)
            // directly use dy/dx will have double type compare precision problem
            Map<String, Integer> map = new HashMap<>();

            for (int j = i + 1; j < len; j++) {
                int diffX = points[j].x - points[i].x;
                int diffY = points[j].y - points[i].y;

                if (diffX == 0 && diffY == 0) {
                    same++;
                } else {
                    // update map
                    int gcd = GCD(diffX, diffY);
                    String key = diffX / gcd + " " + diffY / gcd;
                    int val = map.getOrDefault(key, 0) + 1;
                    // update max point num for cur point
                    max = Math.max(max, val);
                    map.put(key, val);
                }
            }
            // update total max
            res = Math.max(res, max + same);
        }

        return res;
    }

    private int GCD(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return GCD(y, x % y);
        }
    }
}

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
