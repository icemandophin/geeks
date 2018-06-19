/*
similiar to leetcode 56:
1. sort by start time
2. compare prev and cur
if there is gap => [prev.end, cur.start] is free time
*/
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> res = new ArrayList<>();
        List<Interval> a = new ArrayList<>();

        for (List<Interval> p : avails) {
            a.addAll(p);
        }
        // sort all intervals by start time
        Collections.sort(a, (x, y) -> Integer.compare(x.start, y.start));

        Interval prev = a.get(0);

        for (Interval cur : a) {
            if (prev.end < cur.start) {
                res.add(new Interval(prev.end, cur.start));
                prev = cur;
            } else {
                prev.end = Math.max(prev.end, cur.end);
            }
        }

        return res;
    }
}
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
