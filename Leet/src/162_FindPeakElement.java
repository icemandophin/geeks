/*
logarithmic complexity => binary search
condition: top < end
return: end
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int top = 0;
        int end = nums.length - 1;

        while (top < end) {
            int mid = top + (end - top) / 2;
            if (nums[mid] < nums[mid+1]) {
                top = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return end;
    }
}
