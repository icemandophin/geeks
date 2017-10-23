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
equvalent to get max number of non-overlapping intervals in array:
1. sort array with start time => add count (of removal op) for each overlap
2. to minimize count, always keep interval with smaller end time (greedy) during each removal
3. for neighbor intervals, if non-overlapping => make end = cur.end
   else add count and update end = Min{end, cur.end} (indicate remove longer interval)
*/
class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // sort intervals array with start time
        Arrays.sort(intervals, new CompStart());
        int res = 0;
        int min = intervals[0].end;
        // count for overlapping
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start < min) {
                // overlap with prev interval
                res++;
                // greedy choose the shorter interval
                min = Math.max(min, intervals[i].end);
            } else {
                // no overlap, just update min
                min = intervals[i].end;
            }
        }
        return res;
    }
}

class CompStart implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
        return a.start - b.start;
    }
}

/*
Simplify: sort with end time => always keep prev (end time) when overlap
*/
class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // sort intervals array with start time
        Arrays.sort(intervals, new CompEnd());
        int res = 0;
        int min = intervals[0].end;
        // count for overlapping
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start < min) {
                // overlap with prev interval
                res++;
                // since sort by end time, always keep prev interval since it has smaller end time
            } else {
                // no overlap, update min
                min = intervals[i].end;
            }
        }
        return res;
    }
}

class CompEnd implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
        return a.end - b.end;
    }
}
