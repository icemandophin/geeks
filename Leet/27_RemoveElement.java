/*
similar to remove duplicate:
copy a[i] to a[current_end] for mismatch element
*/

class Solution {
    public int removeElement(int[] nums, int val) {
        int pr = 0;
        int i;
        for(i = 0; i < nums.length; i++)
        {
            if(nums[i] != val)
           {
               nums[pr++] = nums[i];
           }
        }

        return pr;
    }
}
