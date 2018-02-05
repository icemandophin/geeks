/*
DFS approach:
1. we choose two numbers (with order) in 12 ways and perform one of 4 operations (12 * 4).
2. with 3 remaining numbers, we choose 2 of them and perform one of 4 operations (6 * 4).
3. two numbers left and make a final choice of 2 * 4 possibilities.
total: 12∗6∗2∗4∗4∗4 = 9216
Notice: be careful to consider both a / b and b / a
*/
class Solution {
    public boolean judgePoint24(int[] nums) {
        // pool contains cur candidate values for calculation
        List<Double> pool = new ArrayList<>();
        // convert to double type for "/"
        for (int num : nums) {
            pool.add((double) num);
        }

        return dfs(pool);
    }
    // 4-3-2 dfs
    private boolean dfs(List<Double> pool) {
        int n = pool.size();

        if (n == 0) {
            return false;
        } else if (n == 1) {
            // check if final result is 24
            return Math.abs(pool.get(0) - 24) < 1e-6;
        } else {
            // select 2 from pool
            // try 4 operators/approaches
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    // do not select same element twice
                    if (i != j) {
                        double x = pool.get(i);
                        double y = pool.get(j);
                        List<Double> next = new ArrayList<>();
                        // add remaining elements to next pool
                        for (int k = 0; k < n; ++k) {
                            if (k != i && k != j) {
                                next.add(pool.get(k));
                            }
                        }
                        // try all 4 operators/approaches
                        // then add to next loop for DFS
                        // for "/" first check if dividend is 0
                        for (int op = 0; op < 4; ++op) {
                            if (op == 0) {
                                next.add(x + y);
                            } else if (op == 1) {
                                next.add(x - y);
                            } else if (op == 2) {
                                next.add(x * y);
                            } else if (y != 0) {
                                // op == 3 => "/"
                                next.add(x / y);
                            } else {
                                continue;
                            }
                            // dfs next pool with 1 less element
                            if (dfs(next)) {
                                return true;
                            } else {
                                // no match => backtrack latest approach
                                next.remove(next.size() - 1);
                            }
                        }
                    }
                }
            }

            return false;
        }
    }
}
