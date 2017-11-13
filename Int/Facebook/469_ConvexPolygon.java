/*
detect angle > 180 degree for all neighbor edges
p1 X p2: (right hand)
> 0: p1 counter-clockwise to p2 < 180
= 0: p1 collinear p2
< 0: p1 counter-clockwise to p2 > 180

For each set of three adjacent points A, B, C, find the cross product AB · BC.
If the sign of all the cross products is the same, the angles are all positive
or negative (depending on the order of visit) the polygon is convex.

corner case: when crossProduct == 0 need to skip and continue
do not accumulate to result since it makes all following results become 0
*/
public class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        boolean negative = false;
        boolean positive = false;
        int size = points.size();

        for (int i = 0; i < size; i++) {
            List<Integer> A = points.get(i);
            // add % size to get first 2 points for last point (rolling)
            List<Integer> B = points.get((i + 1) % size);
            List<Integer> C = points.get((i + 2) % size);

            int crossProduct = getCrossProduct(A, B, C);
            // notice: skip crossProduct == 0 scenario
            if (crossProduct < 0) {
                negative = true;
            } else if (crossProduct > 0) {
                positive = true;
            }
            // detect if det(BA, BC) is always positive/negative
            // both flag are set => there is mismatch
            if (negative && positive) {
                return false;
            }
        }

        return true;
    }
    // calculate BA（a, b） X BC (c, d) = ad - bc
    private int getCrossProduct(List<Integer> A, List<Integer> B, List<Integer> C) {
        return (A.get(0) - B.get(0)) * (C.get(1) - B.get(1)) - (C.get(0) - B.get(0)) * (A.get(1) - B.get(1));
    }
}
