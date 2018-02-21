/*
consider 1st kill scenario:
before : after
   k       0
 k + 1     1
 k + 2     2
      ...
   n      n - k
   1      1 - k + n
   2      2 - k + n
   X      X'

=> compare left/right: X = (X' + k) % n
if x[n] is winner position when there are n people
=> x[n] = (x[n - 1] + k) % n
x[1] = 0 (single player wins)
+1 and match pos to person
*/

public class Solution {
    public int josephus(int n, int k) {
        if (n == 1) {
            return 0;
        }

        int res = 0;
        for (int i = 2; i <= n; ++i) {
            res = (res + k) % i;
        }

        return res + 1;
    }
}
