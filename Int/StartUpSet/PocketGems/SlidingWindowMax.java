/*
keep a descending deque => top always max
*/
class Solution {
    public int[] maxSlidingWindow(int[] a, int k) {
        if ((a == null) || (k <= 0)) {
            return new int [0];
        }

        int n = a.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        int idx = 0;

        for (int i = 0; i < n; ++i) {
            while (!dq.isEmpty() && (dq.peek() < i-k+1)) {
                dq.poll();
            }

            while (!dq.isEmpty() && a[dq.peekLast()] < a[i]) {
                dq.pollLast();
            }

            dq.offer(i);

            if (i >= k - 1) {
                res[idx++] = a[dq.peek()];
            }
        }

        return res;
    }
}
