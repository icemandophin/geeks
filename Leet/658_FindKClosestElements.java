/*
return res that keep element's original order -> cannot use heap
array sorted => binary search to find insertion point, then grow to both sides
O(logn + k) time
*/
class Solution {
    public List<Integer> findClosestElements(int[] a, int k, int x) {
        List<Integer> res = new ArrayList<>();
        // find index of x
        int find = Arrays.binarySearch(a, x);
        if (find < 0) {
            // get index of 1st element that > x
            find = - (find + 1);
        }
        // grow subarray from found index
        // notice top start from find - 1 => move forward one position in final result
        int top = find - 1;
        int end = find;
        while (k -- > 0) {
            // boundary control
            if (top < 0) {
                end++;
            } else if (end >= a.length) {
                top--;
            } else if (Math.abs(a[top] - x) <= Math.abs(a[end] - x)) {
                // top is closer
                top--;
            } else {
                // end is closer
                end++;
            }
        }

        // inject to res - keep original order
        for (int i = top + 1; i < end; ++i) {
            res.add(a[i]);
        }

        return res;
    }
}
