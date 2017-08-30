/*
core question is Fibonacci
Two ways to arrive position N:
last step 1 from position N-1
last step 2 from position N-2
*/

class Solution {
    public int climbStairs(int n) {
        if(n <= 0)
        {
            return 0;
        }
        else if(n <= 2)
        {
            return n;
        }
        else
        {
            int one = 1;
            int two = 2;
            int res = 0;
            int i;

            for(i = 2; i < n; i++)
            {
                res = one + two;
                one = two;
                two = res;
            }

            return res;
        }
    }
}
