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

/*
for the result distinct Interval, the latter one’s start must > previous one’s end
*/
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        int n = intervals.size();

        int[] s = new int[n];
        int[] e = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = intervals.get(i).start;
            e[i] = intervals.get(i).end;
        }
        // sort start/end
        Arrays.sort(s);
        Arrays.sort(e);

        for (int i = 0, j = 0; i < n; ++i) {
            if (i == n - 1 || s[i + 1] > e[i]) {
                res.add(new Interval(s[j], e[i]));
                j = i + 1;
            }
        }

        return res;
    }
}
