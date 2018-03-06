/*
elements from 4 arrays - need diff approach:
"merge" arrays 2 by 2, record sum in hashmap and make it similiar to 2-sum problem
*/
class Solution {
    public int fourSumCount(int[] a, int[] b, int[] c, int[] d) {
        int res = 0;
        int n = a.length;
        Map<Integer, Integer> val = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int sum = a[i] + b[j];
                val.put(sum, val.getOrDefault(sum, 0) + 1);
            }
        }

        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < n; ++y) {
                int expect = - c[x] - d[y];
                if (val.containsKey(expect)) {
                    res += val.get(expect);
                }
            }
        }

        return res;
    }
}
