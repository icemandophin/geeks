/**
 * 给一组meetings(每个meeting由start和end时间组成)。求出在所有输入meeting时间段内没有会议，也就是空闲的时间段.
 * 每个subarray都已经sort好。N个员工，每个员工有若干个interval表示在这段时间是忙碌的。求所有员工都不忙的intervals。
 * For example:
 * [
 *  [[1, 3], [6, 7]],
 *  [[2, 4]],
 *  [[2, 5], [9, 12]]
 * ]
 * Output
 * [[4, 6], [7, 9]]
 *
 * follow up:
 * 求不少于k个员工空闲的时间段（改一下check count的条件就可以了）
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingTime {
  public static void main(String[] args) {
    List<List<Interval>> intervals = new ArrayList<>();
    List<Interval> inter1 = new ArrayList<>();
    inter1.add(new Interval(1, 3));
    inter1.add(new Interval(6, 7));
    List<Interval> inter2 = new ArrayList<>();
    inter2.add(new Interval(2, 4));
    List<Interval> inter3 = new ArrayList<>();
    inter3.add(new Interval(2, 5));
    inter3.add(new Interval(9, 12));
    intervals.add(inter1);
    intervals.add(inter2);
    intervals.add(inter3);

    List<Interval> res = getAvailableIntervals(intervals, 2);
    for (Interval inter : res) {
      System.out.println("[" + inter.start + "," + inter.end + "]");
    }
  }
  public static List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {
    List<Interval> res = new ArrayList<>();
    List<Point> points = new ArrayList<>();
    for (List<Interval> intervalList : intervals) {
      for (Interval interval : intervalList) {
        points.add(new Point(interval.start, true));
        points.add(new Point(interval.end, false));
      }
    }
    Collections.sort(points);

    int count = 0;
    Integer availableStart = null;
    for (int i = 0; i < points.size(); i++) {
      Point point = points.get(i);
      if (point.isStart) {
        count++;
        if (availableStart == null && i == 0 && count <= intervals.size() - k) {
          availableStart = point.time;
        } else if (availableStart != null && count == intervals.size() - k + 1) {
          res.add(new Interval(availableStart, point.time));
          availableStart = null;
        }
      } else {
        count--;
        if (count == intervals.size() - k && i < points.size() - 1) {
          availableStart = point.time;
        } else if (availableStart != null && i == points.size() - 1 && count <= intervals.size() - k) {
          res.add(new Interval(availableStart, point.time));
          availableStart = null;
        }
      }
    }

    return res;
  }
}

class Interval {
  int start;
  int end;
  Interval(int s, int e) {
    start = s;
    end = e;
  }
}

class Point implements Comparable<Point> {
  int time;
  boolean isStart;
  Point(int time, boolean isStart) {
    this.time = time;
    this.isStart = isStart;
  }
  @Override
  public int compareTo(Point that) {
    if (this.time != that.time || this.isStart == that.isStart) {
      return this.time - that.time;
    } else {
      return this.isStart ? -1 : 1;
    }
  }
}
