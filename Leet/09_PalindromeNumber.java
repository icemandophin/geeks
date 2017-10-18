/*
corner case check:
10^n
negative
loop ends when x==y or x/10==y
*/

public class Solution {
    public boolean isPalindrome(int x) {
        if ((x < 0) || (x!=0 && x%10==0))
        {
            return false;
        }

        int rev = 0;
        while (x > rev)
        {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        return (x == rev || x == rev/10);
    }
}
