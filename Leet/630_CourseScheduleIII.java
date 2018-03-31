/*
greedy approach:
1. add courses with earlier deadline first => sort by end time
2. when time + duration > deadline => throw course with so-far max duration
=> need max heap

courses left in max heap is the result
*/
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> { return Integer.compare(a[1], b[1]); });
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        // record cur end time
        int time = 0;

        for (int[] course : courses) {
            time += course[0];
            max.offer(course[0]);
            // check deadline
            if (time > course[1]) {
                // remove course which has max duration so far
                int remove = max.poll();
                // update end time making it earlier
                time -= remove;
            }
        }

        return max.size();
    }
}
