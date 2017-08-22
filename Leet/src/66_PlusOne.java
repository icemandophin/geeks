/*
no need to create carry variable:
if a[i] == 9 => ++a[i] and return a[]
else a[i] = 0 and check a[i-1]
pay attention to last add-bit
*/

class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int i;

        for(i = len-1; i >= 0; i--)
        {
            if(digits[i] < 9)
            {
                ++digits[i];
                return digits;
            }
            else
            {
                digits[i] = 0;
            }
        }

        int[] res = new int [len+1];
        res[0] = 1;

        return res;
    }
}
