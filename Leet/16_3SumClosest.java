/*
similar to 3-sum:
fix one ptr and move 2 pointers in sorted array to get closer to target
based on current sum
*/

class Solution {
    public int threeSumClosest(int[] a, int x) {
        Arrays.sort(a);
        int n = a.length;
        int res = a[0] + a[1] + a[2];
        // for each k traverse from 0 to n - 2
        // move top/end ptr to get closeer to x
        for (int k = 0; k < n - 2; ++k) {
            int top = k + 1;
            int end = n - 1;
            while (top < end) {
                int sum = a[top] + a[end] + a[k];
                if (sum == x) {
                    // perfect - direct return
                    return sum;
                } else if (sum < x) {
                    // increase the sum
                    top++;
                } else {
                    // decrease the sum
                    end--;
                }
                // update closet sum so far
                if (Math.abs(x - sum) < Math.abs(x - res)) {
                    res = sum;
                }
            }
        }

        return res;
    }
}
