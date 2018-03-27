import java.util.*;

public class Solution {
	public static void main(String[] args) {
		List<Point> test = new ArrayList<>();
		test.add(new Point(0, 0));
		test.add(new Point(1, 1));
		test.add(new Point(3, 9));
		System.out.println(Slope.maxSlope(test));
	}
}

class Slope {
	public static double maxSlope(List<Point> a) {
		double res = -Double.MAX_VALUE;
		// sort by x val
		Collections.sort(a, new Comparator<Point>(){
			public int compare(Point a, Point b) {
				return a.x - b.x;
			}
		});
		// then max slope only comes from neighbor points
		for (int i = 0; i < a.size() - 1; ++i) {
			Point cur = a.get(i);
			Point next = a.get(i + 1);
			if (next.x != cur.x) {
				int k = Math.abs((next.y - cur.y) / (next.x - cur.x));
				res = Math.max(res, k);
			}
		}

		return res;
	}
}

class Point {
	int x;
	int y;

	public Point(int i, int j) {
		x = i;
		y = j;
	}
}
