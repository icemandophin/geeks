/*
find a[i], a[j], a[k] => two pointer approach with sorted array
i on top, j on end and move based on sum > or < 0
Notice: remove duplicate result with inner while loop
*/
class Solution {
    public List<List<Integer>> threeSum(int[] a) {
        List<List<Integer>> res = new ArrayList<>();
        if (a == null || a.length < 3) {
            return res;
        }
        int n = a.length;
        // sort array for two-pointer approach
        Arrays.sort(a);
        for (int k = 0; k < n - 2; ++k) {
            if (a[k] > 0) {
                // impossible for k and following to be in solution
                break;
            }
            if (k > 0 && a[k] == a[k - 1]) {
                continue;
            }
            // cur target value
            int target = 0 - a[k];
            // set i, j
            int i = k + 1;
            int j = n - 1;
            while (i < j) {
                int sum = a[i] + a[j];
                if (sum == target) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(a[k]);
                    cur.add(a[i]);
                    cur.add(a[j]);
                    res.add(cur);
                    ++i;
                    --j;
                    // skip dup elements
                    while (i < j && a[i] == a[i - 1]) {
                        ++i;
                    }
                    while (i < j && a[j] == a[j + 1]) {
                        --j;
                    }
                } else if (sum < target) {
                    ++i;
                } else {
                    --j;
                }
            }
        }


        return res;
    }
}
