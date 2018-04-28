package Solution;

// Suppose we want to build a scheduling app. We have the times people are currently busy, e.g.
//   Alice: [(13.5, 14), (15.75, 17)],
//   Bob: [(9, 12), (13, 14), (14, 16)],
//   Eve: [(9, 11), (12.5, 13.5), (14, 15), (16, 18)],
//   Mallory: [(0, 9), (12, 24)]

// For simplicity, lets represent times as numbers between 0 and 24 and the times people are busy as (start_time, end_time) pairs.
// For example, (13.5, 14) means that Alice is busy from 1:30PM-2PM.
// Given a list of people's schedules, write a function to return a list of the time intervals all the people in the list
// are free to meet.

// For the example above, here's some sample output:
//   f([Alice, Bob])  =>  [(0, 9), (12, 13), (17, 24)]
//   f([Bob, Mallory]) => []
//   f([Alice, Bob, Eve]) => [(0, 9), (12, 12.5), (18, 24)]

import java.util.*;

//   Alice: [(13.5, 14), (15.75, 17)],
//   Bob: [(9, 12), (13, 14), (14, 16)],
//   f([Alice, Bob])  =>  [(0, 9), (12, 13), (17, 24)]
class Solution {
    public static void main(String[] args) {
        Map<String, List<Interval>> map = new HashMap<>();
        List<Interval> input1 = new ArrayList<>();
        input1.add(new Interval(13.5, 14));
        input1.add(new Interval(15.75, 17));
        map.put("Alice", input1);

        List<Interval> input2 = new ArrayList<>();
        input1.add(new Interval(9, 12));
        input1.add(new Interval(13, 14));
        input1.add(new Interval(14, 16));
        map.put("Bob", input2);

        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        List<Interval> res = freeTime(names, map);

        for (Interval i : res) {
            System.out.println("start: " + i.start + " end: " + i.end);
        }
    }
    // given list of names and their busy times, return free time for all people
    public static List<Interval> freeTime(List<String> names, Map<String, List<Interval>> map) {
        List<Interval> res = new ArrayList<>();
        // group all intervals
        List<Interval> input = new ArrayList<>();
        for (String name : names) {
            if (map.containsKey(name)) {
                List<Interval> cur = map.get(name);
                for (Interval i : cur) {
                    input.add(i);
                }
            }
        }
        // merge all busy intervals (LC56)
        List<Interval> busy = mergeIntervals(input);
        // generate free time from busy time
        res = getFreeTime(busy);

        return res;
    }
    // sort and merge overlapped intervals
    private static List<Interval> mergeIntervals(List<Interval> input) {
        List<Interval> res = new ArrayList<>();
        // sort intervals per start time
        Collections.sort(input, (a, b) -> (int)Double.compare(a.start, b.start));

        Interval prev = input.get(0);
        for (int i = 1; i < input.size(); ++i) {
            Interval cur = input.get(i);
            if (cur.start > prev.end) {
                res.add(prev);
                prev = cur;
            } else {
                prev.end = Math.max(cur.end, prev.end);
            }
        }
        // add last interval stored in prev!
        res.add(prev);

        return res;
    }
    // find free time slots from busy time slots based on 24hr
    // given sorted non-overlapping intervals
    // 1. check top and end slot
    // 2. add interval slot between 2 busy times
    private static List<Interval> getFreeTime(List<Interval> busy) {
        List<Interval> res = new ArrayList<>();
        int n = busy.size();
        // check head slot
        Double head = new Double(0);
        if (!head.equals(busy.get(0).start)) {
            res.add(new Interval(0, busy.get(0).start));
        }
        // add intervals between busy times
        Interval prev = busy.get(0);
        for (int i = 1; i < n; ++i) {
            Interval cur = busy.get(i);
            // if there is gap slot => add to res
            if (!new Double(prev.end).equals(cur.start)) {
                res.add(new Interval(prev.end, cur.start));
            }
            prev = cur;
        }
        // check tail slot
        Double tail = new Double(24);
        if (!tail.equals(busy.get(n - 1).end)) {
            res.add(new Interval(busy.get(n - 1).end, 24));
        }

        return res;
    }
}

class Interval {
    public double start;
    public double end;

    public Interval(double s, double e) {
        start = s;
        end = e;
    }
}
