/*
for a[i], a[j] water vol = min{a[i], a[j]} * (j - i)
*/

class Solution {
    public int maxArea(int[] h) {
        int res = 0;
        int top = 0;
        int end = h.length - 1;

        while (top < end) {
            res = Math.max(res, (end - top) * Math.min(h[top], h[end]));
            if (h[top] < h[end]) {
                top++;
            } else {
                end--;
            }
        }

        return res;
    }
}
