/*
two pointer approach:
let f(i) = a[i] => verify f(i) == f(j) exist
f(i) == f(j) => f{...f(i)} == f{...f(j)}
=> pointer with diff speed will always meet

ref: http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
*/
class Solution {
    public int findDuplicate(int[] a) {
        int slow = 0;
        int fast = 0;

        while (true) {
            // fast with 2x speed
            slow = a[slow];
            fast = a[a[fast]];

            if (slow == fast) {
                break;
            }
        }
        // find intersection which is repeated num
        slow = 0;
        while (slow != fast) {
            slow = a[slow];
            fast = a[fast];
        }

        return slow;
    }
}

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
