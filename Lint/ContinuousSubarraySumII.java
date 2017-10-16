public class Solution {
    /*
    two scenarios for max subarray:
     */
     public List<Integer> continuousSubarraySumII(int[] a) {
        ArrayList<Integer> res = new ArrayList<>();
        // init with a[0]
        res.add(0);
        res.add(0);
        if (a == null || a.length == 0) {
            return res;
        }
        int top = 0;
        int end = 0;
        // cur sum
        int cur = 0;
        // cur max value
        int bar = Integer.MIN_VALUE;
        int total = 0;
        int i;

        // find max continuous subarray sum without circle
        // same problem as Continuous Subarray Sum I
        // calculate sum of array at the same time
        for (i = 0; i < a.length; ++i) {
            total += a[i];
            if (cur < 0) {
                // adjust subarray from new start
                top = end = i;
                cur = a[i];
            }
            else {
                // grow existing subarray
                end = i;
                cur += a[i];
            }
            // check if cur sum is larger than cur max
            if (cur >= bar) {
                // update cur max and result
                bar = cur;
                res.set(0, top);
                res.set(1, end);
            }
        }
        // find max subarray in a[] with circle
        // equals to find MIN subarray in a[] without circle
        // end index of min subarray will be start index of circular max subarray
        top = 0;
        end = -1;
        cur = 0;
        for (i = 0; i < a.length; ++i) {
            if (cur <= 0) {
                // grow min subarray
                end = i;
                cur += a[i];
            }
            else {
                top = end = i;
                cur = a[i];
            }
            // handle all negative case
            if (top == 0 && end == a.length - 1) {
                // entire array is negative
                // will return result of above scenario
                continue;
            }
            // check if cur min makes remaining bigger sum than bar
            if (total - cur > bar) {
                bar = total - cur;
                // take mod for top/end index to avoid exceed boundary
                res.set(0, (end + 1) % a.length);
                res.set(1, (top - 1 + a.length) % a.length);
            }
        }

        return res;
    }
}
