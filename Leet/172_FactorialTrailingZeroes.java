/*
10 = 2 * 5 and there is always more 2 than 5 in n
=> cnt of 5 determines num of 0
*/
public class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        // count num of 5^x factor from 1 to n
        // their sum is total num of 5s that can make 10
        for (long i = 5; i <= n; i *= 5) {
            count += n / i;
        }

        return count;
    }
}
