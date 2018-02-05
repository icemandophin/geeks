/*
find pattern:

num  binary  cnt
-------------
1    0001    1
-------------
2    0010    1
3    0011    2
-------------
4    0100    1
5    0101    2
6    0110    2
7    0111    3
-------------
8    1000    1
9    1001    2
10   1010    2
11   1011    3
12   1100    2
13   1101    3
14   1110    3
15   1111    4

n % 2 == 0 => cnt(n) = cnt(n / 2)
n % 2 == 1 => cnt(n) = cnt(n / 2) + 1
*/
class Solution {
    public int[] countBits(int n) {
        // build res as dp
        int[] res = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            // can replace with res[i] = res[i >> 1] + (i & 1);
            if (i % 2 == 0) {
                res[i] = res[i / 2];
            } else {
                res[i] = res[i / 2] + 1;
            }
        }

        return res;
    }
}

/*
optimize:
n    binary  cnt  i & (i-1)
0    0000    0
-----------------------
1    0001    1    0000
-----------------------
2    0010    1    0000
3    0011    2    0010
-----------------------
4    0100    1    0000
5    0101    2    0100
6    0110    2    0100
7    0111    3    0110
-----------------------
8    1000    1    0000
9    1001    2    1000
10   1010    2    1000
11   1011    3    1010
12   1100    2    1000
13   1101    3    1100
14   1110    3    1100
15   1111    4    1110

i & (i - 1) is used to check 2^n
notice that cnt[i] = cnt[i & (i - 1)] + 1
*/
public class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            res[i] = res[i & (i - 1)] + 1;
        }

        return res;
    }
}
