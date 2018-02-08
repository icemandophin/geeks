package solution;
import java.lang.management.ManagementPermission;
import java.util.*;
// System.out.println("res: ");

import javax.imageio.ImageTypeSpecifier;

public class Solution {
	public static void main(String[] args) {
		String s = "I am using hackerrank to improve programming is it";
		String t = "am hackerrank to improve";
		String[] out = missingWords(s, t);
		for (String x : out) {
			System.out.println(x);
		}
	}

	static String[] missingWords(String s, String t) {
		if (s == null) {
			return null;
		} else if (t== null) {		
			return s.split(" ");
		} else {
			String[] sa = s.split(" ");
			String[] ta = t.split(" ");
			List<String> res = new ArrayList<>();

			for (int i = 0, j = 0; i < sa.length; ++i) {
				if (j < ta.length && sa[i].equals(ta[j])) {
					j++;
				} else {
					res.add(sa[i]);
				}
			}

			return res.toArray(new String[res.size()]);
		}
	}
}
