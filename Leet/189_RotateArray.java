/*
handle like substring:
1234567 -> 765 4321
-> 567 1234
*/
public class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        // reverse all elements
        reverse(nums, 0, len - 1);
        // reverse first k and remaining parts separately
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
