/*
binary search: O(nlog(n)) time, O(1) space
count number of
*/
class Solution {
    public int findDuplicate(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int top = 0;
        int end = a.length - 1;
        // count a[i] <= mid and search sub scope
        while (top < end) {
            // get mid value in [top, end]
            int mid = top + (end - top) / 2;
            int cnt = 0;
            for (int i : a) {
                if (i <= mid) {
                    cnt++;
                }
            }
            // search in sub scope
            if (cnt <= mid) {
                top = mid + 1;
            } else {
                end = mid;
            }
        }

        return top;
    }
}

/*
optimize: O(n) time
ref: http://keithschwarz.com/interesting/code/?dir=find-duplicate
*/
public class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) {
                break;
            }
        }

        slow = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
