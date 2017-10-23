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
If new one end before min_start or start after max_end => insert
Else find the insert slot: 1st end_i > new_start
merge all following j where start_j < new_end
merge_start: min merge_end: max
add non-overlapping prev and after intervals to res
*/
class Solution {
    public List<Interval> insert(List<Interval> ints, Interval newInt) {
        List<Interval> res = new LinkedList<>();
        int i = 0;
        int n = ints.size();
        // traverse ints and add all prev non-overlapping ones to res
        while (i < n && ints.get(i).end < newInt.start) {
            res.add(ints.get(i++));
        }
        // define start/end for merged interval
        int ms = newInt.start;
        int me = newInt.end;
        // merge overlapping intervals with new one and insert to res
        while (i < n && ints.get(i).start <= newInt.end) {
            // update start & end time for merged interval
            ms = Math.min(ints.get(i).start, ms);
            me = Math.max(ints.get(i).end, me);
            i++;
        }
        Interval merged = new Interval(ms, me);
        res.add(merged);
        // add folloing non-overlapping ones to res
        while (i < n) {
            res.add(ints.get(i++));
        }
        return res;
    }
}

/*
optimize space => merge in place approach:
*/
class Solution {
    public List<Interval> insert(List<Interval> ints, Interval newInt) {
        int i = 0;
        // traverse ints and add all prev non-overlapping ones to res
        while (i < ints.size() && ints.get(i).end < newInt.start) {
            i++;
        }
        // define start/end for merged interval
        int ms = newInt.start;
        int me = newInt.end;
        // notice that change happens in ints now, hence size of ints shrinking
        // need to keep checking ints.size() in loop
        while (i < ints.size() && ints.get(i).start <= newInt.end) {
            ms = Math.min(ints.get(i).start, ms);
            me = Math.max(ints.get(i).end, me);
            // remove original one from ints
            ints.remove(i);
            // now (i+1)th becomes new ith element, no need for i++
        }
        Interval merged = new Interval(ms, me);
        // need to add it to i-th position
        ints.add(i, merged);
        // following are non-overlapping ones, no need to handle
        return ints;
    }
}
