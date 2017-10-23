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
 always check if cur.start > prev.end
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }
        // sort intervals with start time first
         Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        List<Interval> res = new ArrayList<>();
        Interval prev = intervals.get(0);
        // check each interval with its prev
        for (int i = 1; i < intervals.size(); ++i) {
            Interval cur = intervals.get(i);
            if (cur.start > prev.end) {
                // no overlap, add existing one to res
                res.add(prev);
                // update existing
                prev = cur;
            } else {
                // merge both by extending end time of existing/prev
                // include prev cover cur scenario
                prev.end = Math.max(prev.end, cur.end);
            }
        }
        // insert last one
        res.add(prev);

        return res;
    }
}
