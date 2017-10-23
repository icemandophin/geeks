/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
 //
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        // create start & end array
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        // sort ascending
        Arrays.sort(start);
        Arrays.sort(end);
        int res = 0;
        int endIdx = 0;
        for (int i = 0; i < start.length; i++) {
            if (start[i] < end[endIdx]) {
                // new start meeting cannot reuse prev meeting's room
                res++;
            }
            else {
                // cur meeting can reuse last meeting's room
                // update meeting end time to new meeting
                endIdx++;
            }
        }

        return res;
    }
}
