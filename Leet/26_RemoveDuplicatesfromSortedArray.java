/*
only when a[i] != a[i-1]:
copy a[i] to a[ptr]
move ptr forward
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int nt = 1;
        int i;

        if(len <= 1)
        {
            return len;
        }

        for(i = 1; i < len; i++)
        {
            if(nums[i] != nums[i-1])
            {
                nums[nt++] = nums[i];
            }
        }

        return nt;
    }
}
