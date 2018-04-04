/*
Moore voting: O(N) time O(1) space
1. set 1st element as res
2. if element repeat cnt++ else cnt--
3. when cnt == 0 reset res to a[i]
=> Majority Element will always be captured
*/
class Solution {
    public int majorityElement(int[] nums) {
        int res = 0;
        int cnt = 0;

        for (int a : nums) {
            if (cnt == 0) {
                // init voting
                res = a;
                cnt = 1;
            } else if (a == res) {
                cnt++;
            } else {
                cnt--;
            }
        }

        return res;
    }
}
