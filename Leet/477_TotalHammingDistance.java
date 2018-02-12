/*
eg:
4:     0 1 0 0
14:    1 1 1 0
2:     0 0 1 0
1:     0 0 0 1

=> for last col Hdistance is 3 = 1 + 1 + 1
1 makes distance 1 with each of 3 0s
=> for each col Hdistance = num_of_1 * num_of_0
*/
class Solution {
    public int totalHammingDistance(int[] a) {
        int res = 0;
        int n = a.length;

        for (int i = 0; i < 32; ++i) {
            // count # of 1 in col i
            int cnt = 0;
            for (int x : a) {
                if ((x >>> i & 1) == 1)  {
                    ++cnt;
                }
            }
            // # of 0 is n - cnt
            // add to res
            res += cnt * (n - cnt);
        }

        return res;
    }
}
