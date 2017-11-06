/*
1 Shift non-zero values as far forward as possible
2 Fill remaining space with zeros
*/
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // j marks cur position available for inserting non-zero number
        int j = 0;
        for (int num: nums) {
            // shift non-zero numbers to the left
            if (num != 0) {
                nums[j++] = num;
            }
        }
        // current j pointers to next position of last non-zero number
        // all following should be mare
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }
}

/*
two pointers:
i traverse array, j marks cur 0 index
when a[i] != 0 then swap a[i] & a[j]
*/
class Solution {
    public void moveZeroes(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int j = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != 0) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                j++;
            }
        }
    }
}
