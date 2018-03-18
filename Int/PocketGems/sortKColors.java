/*
two pointer approach: O(n^2): T(n) = T(n - 2) + n

Doing quick sort partition for K-1 times
Each time sort the array into three parts:
[all min] [all unsorted others] [all max]
=> update min and max and sort the [all unsorted others]

1. Use K - 1 value as pivot
2. Starting from 0, whenever low<high && less or equal to pivot, low++
3. starting from end, whenever high >0, and greater than pivot, high--
4. Result: only swap when low and high have disagreement on the pivot value.
*/
class Solution {
    public void sortColors2(int[] colors, int k) {
        int top = 0;
        int end = colors.length - 1;
        int i = 0;
        int min = 1, max = k;
        while (min < max) {
            // sort min/max to top/end of array
            while (i <= end) {
                if (colors[i] == min) {
                    swap(colors, top, i);
                    i++;
                    top++;
                } else if (colors[i] == max) {
                    swap(colors, end, i);
                    end--;
                } else {
                    i++;
                }
            }
            // update value of min max and i
            // to sort subarray
            i = top;
            min++;
            max--;
        }
    }

    public void swap(int[] colors, int x, int y){
        int temp = colors[x];
        colors[x] = colors[y];
        colors[y] = temp;
    }
}

/*
in-place bucket sort:
*/
class Solution {
    public void sortKColors(int[] colors, int k) {
        if (colors == null) {
            return;
        }

        int len = colors.length;
        for (int i = 0; i < len; i++) {
            // Means need to deal with A[i]
            while (colors[i] > 0) {
                int num = colors[i];
                if (colors[num - 1] > 0) {
                    // 1. There is a number in the bucket,
                    // Store the number in the bucket in position i;
                    colors[i] = colors[num - 1];
                    colors[num - 1] = -1;
                } else if (colors[num - 1] <= 0) {
                    // 2. Bucket is using or the bucket is empty.
                    colors[num - 1]--;
                    // delete the A[i];
                    colors[i] = 0;
                }
            }
        }

        int index = len - 1;
        for (int i = k - 1; i >= 0; i--) {
            int cnt = -colors[i];

            // Empty number.
            if (cnt == 0) {
                continue;
            }

            while (cnt > 0) {
                colors[index--] = i + 1;
                cnt--;
            }
        }
    }
}
