/*
DP approach: O(N)
let max_product[i] be max subarray product from 0 to i, similiar defn for min_product[i]
max_product[i] = Max{max_product[i-1] * a[i], min_product[i-1] * a[i], a[i]}
min_product[i] = Min{max_product[i-1] * a[i], min_product[i-1] * a[i], a[i]}
Optimize with rolling list: O(1)
notice max_product[i]/min_product[i] only depend on max_product[i-1], min_product[i-1] and a[i]
hence update curMax and curMin based on a[i]>0 or not
corner case: start from a[1] to avoid error with single element array
*/
class Solution {
    public int maxProduct(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int res = a[0];
        int curMin = a[0];
        int curMax = a[0];
        int i;
        int prevMax, prevMin;
        // start from a[1] to avoid error
        for (i = 1; i < a.length; ++i) {
            // save current value before update curMax/curMin
            prevMax = curMax;
            prevMin = curMin;
            curMax = Math.max(a[i], Math.max(prevMax*a[i], prevMin*a[i]));
            curMin = Math.min(a[i], Math.min(prevMax*a[i], prevMin*a[i]));
            // update result with each curMax value
            res = Math.max(res, curMax);
        }
        return res;
    }
}
