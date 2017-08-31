/*
only need to add remaining part of nums2 to nums1 after 1st loop
*/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int res = m + n - 1;
        int ap = m - 1;
        int bp = n - 1;

        while((ap >= 0) && (bp >= 0))
        {
            if(nums1[ap] > nums2[bp])
            {
                nums1[res--] = nums1[ap--];
            }
            else
            {
                nums1[res--] = nums2[bp--];
            }
        }

        while(bp >= 0)
        {
           nums1[res--] = nums2[bp--];
        }
    }
}
