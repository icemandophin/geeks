/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class SummaryRanges {
    // implement RB tree with TreeMap
    // so that put()/remove()/lowerKey()/higherKey() are O(logN)
    // key is interval.start
    private TreeMap <Integer, Interval>map;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }
    /*
    |____________|          |_________________________________|
   low        low.end      high                           high.end

               |______________|
              val            val
    */
    public void addNum(int val) {
        if (map.containsKey(val)) {
            return;
        }
        // search 2 existing intervals next to new add one
        // use class for null scenario check
        Integer low = map.lowerKey(val);
        Integer high = map.higherKey(val);
        // check and merge new one with 2 boundaries, if exist
        if (low != null && high != null && val == map.get(low).end + 1 && val == high - 1) {
            // new one just connect low and high interval
            // extend low and remove high
            map.get(low).end = map.get(high).end;
            map.remove(high);
        } else if (low != null && val <= map.get(low).end + 1) {
            // overlap with low, update its boundary
            map.get(low).end = Math.max(map.get(low).end, val);
        } else if (high != null && val == high - 1) {
            // overlap with high => merge start time
            // replace high with updated node since start is used as key
            map.put(val, new Interval(val, map.get(high).end));
            map.remove(high);
        } else {
            // should cover all non-overlapping scenario
            // including low/high exist but distance > 2
            // insert new one
            map.put(val, new Interval(val, val));
        }
    }

    public List<Interval> getIntervals() {
        // convert tree values to list
        return new ArrayList<>(map.values());
    }
}
