/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

 /*
 sort by start time and check overlap (cur.start < prev.end)
 */
public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                return false;
            }
        }

        return true;
    }
}
