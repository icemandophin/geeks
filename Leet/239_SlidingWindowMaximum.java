/*
deque approach to save "useful" index: O(N)
make index in deque descending order
for current a[i], window range is [i-k+1, i]
from top: always poll index that out of range
from end: always poll j that a[j] < new element (cannot be max)
          then insert new element
the head of deque is always index of current max value in window
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if ((nums == null) || (k <= 0)) {
            return new int [0];
        }
        int n = nums.length;
        int[] res = new int[n-k+1];
        int ri = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            // remove outdated indexes
            while (!q.isEmpty() && (q.peek() < i-k+1)) {
                q.poll();
            }
            // keep deque descending
            while (!q.isEmpty() && (nums[q.peekLast()] < nums[i])) {
                q.pollLast();
            }
            q.offer(i);
            if (i >= k-1) {
                // head element always cur max
                res[ri++] = nums[q.peek()];
            }
        }
        return res;
    }
}

/*
optimized: keep descending order with priority queue
*/
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k <= 0) {
            return new int[0];
        }

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int len = nums.length;
        int[] result = new int[len - k + 1];

        for (int i = 0; i < len; i++) {
            queue.offer(nums[i]);

            if (i >= k - 1) {
                // insert result and remove last / expired element
                result[i - k + 1] = queue.peek();
                queue.remove(nums[i - k + 1]);
            }
        }

        return result;
    }
}
