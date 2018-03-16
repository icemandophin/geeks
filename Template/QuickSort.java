public class Solution {
    public void quickSort(int[] array) {
        if (array == null) {
            return;
        }

        helper(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private void helper(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = array[start];
        int low = start + 1;
        int high = end;

           while (low <= high) {
               if (array[low] < pivot) {
                   low++;
               } else if (array[high] >= pivot) {
                   high--;
               } else {
                   int temp = array[low];
                   array[low] = array[high];
                   array[high] = temp;
                   low++;
                   high--;
               }
           }

        array[start] = array[high];
        array[high] = pivot;

        helper(array, start, high - 1);
        helper(array, high + 1, end);
    }
}
