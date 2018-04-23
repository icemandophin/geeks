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
start/end time approach:
1. for each interval, mark start time spot with 1
end time spot with -1
2. traverse all spots and update count, record max count as res
*/
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        List<int[]> list = new ArrayList<>();
        int count = 0;
        int result = 0;
        // mark each start/end time point
        for (Interval i : intervals) {
            list.add(new int[] { i.start, 1 });
            list.add(new int[] { i.end, -1});
        }
        // sort time spots
        // for same time put start befoe end
        list.sort((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        // update count and record max
        for (int[] pair : list) {
            count += pair[1];
            result = Math.max(result, count);
        }

        return result;
    }
}

/*
detect overlap:
1. sort start/end time Array
2. for each overlap detected => add room count
*/
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
