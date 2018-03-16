public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        Integer[] result = new Integer[len];
        Arrays.fill(result, 0);

        mergeSort(nums, 0, len - 1, result);

        return Arrays.asList(result);
    }

    private int[][] mergeSort(int[] nums, int start, int end, Integer[] result) {
        if (start > end) {
            return new int[0][0];
        } else if (start == end) {
            return new int[][] { { nums[start], start } };
        } else {
            int mid = start + (end - start) / 2;
            int m = mid - start + 1;
            int n = end - mid;

            int[][] left = mergeSort(nums, start, mid, result);
            int[][] right = mergeSort(nums, mid + 1, end, result);
            int[][] sorted = new int[m + n][2];
            int i = 0;
            int j = 0;

            for (int k = 0; k < m + n; k++) {
                if (i == m || (j < n && left[i][0] > right[j][0])) {
                    sorted[k] = right[j++];
                } else {
                    result[left[i][1]] += j;
                    sorted[k] = left[i++];
                }
            }

            return sorted;
        }
    }
}
