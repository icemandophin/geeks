package Solution;
import java.util.*;

public class Solution {
  public static void main(String[] args) {
      Interval a1 = new Interval(-1, 9);
      Interval a2 = new Interval(1, 10);
      Interval a3 = new Interval(0, 3);
      Interval a4 = new Interval(9, 10);
      Interval a5 = new Interval(3, 14);
      Interval a6 = new Interval(2, 9);
      Interval a7 = new Interval(10, 16);
      Interval target = new Interval(2, 15);

      Interval[] input = {a1, a2, a3, a4, a5, a6, a7};
      CoverInterval test = new CoverInterval();
      System.out.println(test.find(input, target));
  }
}

class CoverInterval {
    public int find (Interval[] a, Interval x) {
        if (a == null || a.length == 0) {
            return -1;
        }

        Arrays.sort(a, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b) {
                return Integer.compare(a.start, b.start);
            }
        });

        int res = 0;
        int i = 0;
        int s = x.start;

        while (i < a.length) {
            // get interval(index >= i) that start before target
            // and has longest end
            // to minimize interval needed
            int cur = greedy(a, s, i);
            // add cur interval to merge set
            res++;

            if (a[cur].end >= x.end) {
                // target covered
                return res;
            }
            // need new interval for extension
            i = cur;
            s = a[cur].end;
        }

        return -1;
    }
    // find interval that cover start time s
    // with longest end time
    private int greedy (Interval[] a, int s, int i) {
        int res = i;

        while (i < a.length) {
            if (a[i].start <= s && a[i].end > a[res].end) {
                res = i;
            } else if (a[i].start > s) {
                return res;
            }

            i++;
        }

        return res;
    }
}

class Interval {
    public int start;
    public int end;
    public Interval (int a, int b) {
        start = a;
        end = b;
    }
}
