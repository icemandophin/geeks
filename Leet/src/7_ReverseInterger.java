public class Solution {
    public int reverse(int x) {
        int res = 0;

        while (x != 0)
        {
            int tail = x % 10;
            int tmp = res *10 + tail;
            // if overflow then current tmp != current res
            // another way is: tmp > Integer.MAX_VALUE || tmp < Integer.MIN_VALUE
            if ((tmp - tail) / 10 != res )
            {
                res = 0;
                break;
            }

            res = tmp;
            x = x / 10;
        }
        return res;
    }
}