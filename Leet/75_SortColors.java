/*
expected output like 0000000011111111111111122222222
use top to mark first index of 0 subarray, end to mark last index of 2 subarray
for each a[i] = 0 swap with top and increase top
for each a[i] = 2 swap with end and decrease end
for a[i] = 1 skip
Notice: a[i] = 2 cannot increase i, since a[i] can be 0 after swap, and require further swap with top
*/
class Solution {
    public void sortColors(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        int n = a.length;
        int top = 0;
        int end = n - 1;
        int i = 0;
        while (i <= end) {
            if (a[i] == 0) {
                // swap 0 to top and increase 0-array
                // also increase i
                swap(a, i++, top++);
            } else if (a[i] == 2) {
                // swap 0 to end and decrease 2-array
                // do NOT increase i since further swap with top might needed
                swap(a, i, end--);
            } else {
                // skip
                i++;

            }
        }
    }
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
