package solution;
import java.io.ObjectOutputStream.PutField;
import java.rmi.server.Skeleton;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.ResultSetMetaData;
import java.util.*;
import javax.activation.MailcapCommandMap;
import javax.print.attribute.standard.Finishings;
//System.out.println();
import javax.print.attribute.standard.RequestingUserName;

import org.omg.CORBA.UnionMember;

/*
 * two iterators: one traverse list, the other traverse each element of cur list
 */
public class Solution {
	public static void main(String[] args) {
	}
}

class PaintElements {
	Map<Integer, Integer> root = new HashMap<>();
	Map<Integer, String> map = new HashMap<>();

	public List<Paint> PaintElements(List<Paint> paints, List<Link> pairs) {
		List<Paint> res = new ArrayList<>();

		for (Paint p : paints) {
			map.put(p.val, p.color);
			root.put(p.val, p.val);
		}

		for (Link cur : pairs) {
			int x = cur.a;
			int y = cur.b;

			if (!root.containsKey(x)) {
				root.put(x, Integer.MAX_VALUE);
			}
			if (!root.containsKey(y)) {
				root.put(y, Integer.MAX_VALUE);
			}

			int fx = find(x);
			int fy = find(y);
			if (fx == Integer.MAX_VALUE && fy == Integer.MAX_VALUE) {
				union(fy, fx);
			} else if (fx == Integer.MAX_VALUE) {
				union(fx, fy);
				map.put(x, map.get(fy));
			} else if (fy == Integer.MAX_VALUE) {
				union(fy, fx);
				map.put(y, map.get(fx));
			} else {
				if (fx != fy) {
					// detect conflict
					return res;
				}
			}
		}

		for (Map.Entry<Integer, String> one : map.entrySet()) {
			res.add(new Paint(one.getKey(), one.getValue()));
		}

		return res;
	}

	private int find(int x) {
		int res = root.get(x);

		if (res != x && res != Integer.MAX_VALUE) {
			res = find(res);
			root.put(x, res);
		}

		return res;
	}

	private void union(int a, int b) {
		root.put(a, b);
	}
}

class Paint {
	public Integer val;
	public String color;

	public Paint(int v, String c) {
		val = val;
		color = c;
	}
}

class Link {
	public int a;
	public int b;

	public Link(int x, int y) {
		a = x;
		b = y;
	}
}
