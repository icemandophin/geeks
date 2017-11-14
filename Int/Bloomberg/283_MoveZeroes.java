/*
Shift non-zero values as far forward as possible
Fill remaining space with zeros
*/
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // index to shift non-zero elements back
        // nums[insertPos] != 0 => fill by itself
        // nums[insertPos] == 0 => filled with following non-zero value
        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
        // following elements should be filled with 0
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
