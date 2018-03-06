/*
extend from 3-sum by adding one layer
add duplicate check for each of i, j , x, y
*/
class Solution {
    public List<List<Integer>> fourSum(int[] a, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = a.length;
        // sort as preprocess
        Arrays.sort(a);

        for (int i = 0; i < n - 3; ++i) {
            // skip dup i val
            if (i == 0 || a[i] != a[i - 1]) {
                for (int j = i + 1; j < n - 2; ++j) {
                    // skip dup j val
                    if (j == i + 1 || a[j] != a[j - 1]) {
                        // tow pointer approach
                        int x = j + 1;
                        int y = n - 1;

                        while (x < y) {
                            int sum = a[i] + a[j] + a[x] + a[y];
                            if (sum == target) {
                                res.add(Arrays.asList(a[i], a[j], a[x], a[y]));
                                x++;
                                y--;
                                // skip dup x, y
                                while (x < y && a[x] == a[x - 1]) {
                                    x++;
                                }

                                while (x < y && a[y] == a[y + 1]) {
                                    y--;
                                }
                            } else if (sum < target) {
                                x++;
                            } else {
                                y--;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }
}
