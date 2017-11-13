/*
Fisher–Yates shuffle
*/
public class Solution {
    private int[] nums;
    private Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null) return null;
        int[] res = nums.clone();
        for(int j = 1; j < res.length; j++) {
            // generate random number i in [0, j]
            int i = random.nextInt(j + 1);
            swap(res, i, j);
        }
        return res;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
