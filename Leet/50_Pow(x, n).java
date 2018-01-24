/*
recursive: partition in half to reuse x^(n/2) result
Notice the edge condition: n < 0 => -n will overflow when n = Integer.MIN_VALUE
*/
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if(n == 1) {
            return x;
        }
        // cover n < 0 scenario
        // let t = n/2 then flip signal
        // to avoid possible overflow
        int t = n / 2;
        if(n < 0) {
            t = -t;
            x = 1 / x;
        }
        // save x^(n/2) for reuse
        double half = myPow(x, t);
        if(n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
