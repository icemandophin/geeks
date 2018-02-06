/*
greedy:
each time pick and exec task with most count
else add idle interval for cool down
*/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // count each char's freq
        int[] cnt = new int[26];
        // sort char with highest freq at top
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        // min number of intervals to make the array
        int res = 0;
        // get counts
        for (char t : tasks) {
            cnt[t - 'A']++;
        }
        // sort count/freq descending
        for (int c : cnt) {
            if (c > 0) {
                queue.offer(c);
            }
        }
        // in each cycle exec some task
        // and save remaining for next
        while (!queue.isEmpty()) {
            List<Integer> next = new ArrayList<>();
            // n is used as cycle length
            // filled with tasks and remaining is idle
            for (int i = 0; i <= n; ++i) {
                if (!queue.isEmpty()) {
                    // get cur most cnt
                    int cur = queue.poll();
                    if (cur > 1) {
                        // cur - 1 => exec cur
                        // and save remaining for next cycle
                        // then move to next task/cnt
                        next.add(cur - 1);
                    }
                }
                // add interval cnt for task/idle
                res++;
                // no further task to add
                if (queue.isEmpty() && next.isEmpty()) {
                    break;
                }
            }
            // move to next cycle
            // queue is sorted => next is also sorted
            queue.addAll(next);
        }

        return res;
    }
}
