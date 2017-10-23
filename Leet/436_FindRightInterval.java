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
1. build hash map for start -> index
2. sort start time array
3. for each element i, binary search for j where j.start >= i.end => res[i] = hash(j)
*/
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> starts = new ArrayList<>();
        int n = intervals.length;
        int[] res = new int[n];
        int i;
        // int hash and array
        for (i = 0; i < n; ++i) {
            int start = intervals[i].start;
            map.put(start, i);
            starts.add(start);
        }
        // sort start array
        Collections.sort(starts);
        // search "nearest" right interval for each element
        for (i = 0; i < n; ++i) {
            // set cur target
            int end = intervals[i].end;
            // search closet start time that >= end time
            int find = Collections.binarySearch(starts, end);
            if (find < 0) {
                // no starts[j] == end, return -(idx + 1) where
                // idx is the 1st element > end => idx = -(find + 1)
                // which is what we need
                find = -(find + 1);
            }
            // handle index found
            if (find == n) {
                // all starts < end => no right interval
                res[i] = -1;
            } else {
                // found start[j] == end => hash index
                res[i] = map.get(starts.get(find));
            }
        }
        return res;
    }
}

/*
simplify with TreeMap
*/
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = intervals.length;
        int[] res = new int[n];
        int i;
        // build tree map with start time as key, index as value
        for (i = 0; i < n; ++i) {
            map.put(intervals[i].start, i);
        }
        // traserse intervals and find 1st j.start >= i.end
        for (i = 0; i < n; ++i) {
            // find smallest key/start time that start >= target end
            Integer find = map.ceilingKey(intervals[i].end);
            if (find == null) {
                // all start < end
                res[i] = -1;
            } else {
                // get match node's value/index
                res[i] = map.get(find);
            }
        }
        return res;
    }
}
