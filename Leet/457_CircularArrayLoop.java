class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        // if a[i] % n == 0 => getNext(nums, i) == i
        // it moves back to origin place
        for (int i = 0; i < n; ++i) {
            if (nums[i] % n == 0) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (nums[i] != 0) {
                // try to start from cur index
                int slow = i;
                int fast = i;
                // ensure both pointers move to same direction
                while (isValid(nums, i, getNext(nums, fast)) && isValid(nums, i, getNext(nums, getNext(nums, fast)))) {
                    // fast moves twice for each iteration
                    slow = getNext(nums, slow);
                    fast = getNext(nums, getNext(nums, fast));
                    // similiar to linked-list loop detection
                    if (slow == fast) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    // given index move to next element
    private int getNext(int[] nums, int i) {
        int n = nums.length;
        // %n for circular array
        int next = (i + nums[i]) % n;
        // next should be in [0 : n) => add n if next < 0
        if (next < 0) {
            return next + n;
        } else {
            return next;
        }
    }
    // check if a[i] * a[j] > 0 => i, j both move forward
    private boolean isValid(int[] nums, int i, int j) {
        return nums[j] != 0 && ((nums[i] ^ nums[j]) >> 31) == 0;
    }
}
