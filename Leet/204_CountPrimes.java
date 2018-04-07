/*
composite number can always be represented by smaller prime number
=> traverse and mark all composites numbers each prime number can make
*/
class Solution {
    public int countPrimes(int n) {
        // mark if each number is prime number
        boolean[] p = new boolean[n];
        // record # of prime number so far
        int res = 0;

        for (int i = 2; i < n; ++i) {
            if (!p[i]) {
                // for each prime number
                // (1) add count (2) set all composite number it can make
                res++;
                for (int j = i; j < n; j += i) {
                    p[j] = true;
                }
            }
        }

        return res;
    }
}
