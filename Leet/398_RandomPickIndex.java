class Solution {
    int[] a;
    Random rand;
    // constructor
    public Solution(int[] nums) {
        this.a = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int res = -1;
        int cnt = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != target) {
                continue;
            }
            // when a[i] == target => add cnt
            ++cnt;
            // generate random number [0 : cnt)
            // if it is one specific number (like 1st one) refresh res
            // by this approach it one of the matching index will be chosen randomly
            if (rand.nextInt(cnt) == 0) {
                res = i;
            }
        }

        return res;
    }
}
