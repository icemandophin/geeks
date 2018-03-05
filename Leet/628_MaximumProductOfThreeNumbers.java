/*
sort array => max abs elements on top/end
consider position of first/last 3 elements and 0:
turns out max can only be possible by last 3 or first 2 (when negative) plus last one
=> only need to record max 3 and min 2 elements
*/
class Solution {
    public int maximumProduct(int[] a) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        for (int x : a) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(max1 * min1 * min2, max1 * max2 * max3);
    }
}
