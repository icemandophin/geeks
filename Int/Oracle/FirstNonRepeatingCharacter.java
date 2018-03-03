import java.util.*;
// System.out.println("res: ");

import javax.activation.MailcapCommandMap;

public class Solution {
	public static void main(String[] args) {
		String str = "geeksforgeeks";

		System.out.println(FirstUnique.firstUnique(str));
	}
}

class FirstUnique {
	public static char firstUnique(String str) {
		char res = '*';
		// key is char => size <= 256
		Map<Character, Rec> map = new HashMap<>();
		char[] ch = str.toCharArray();

		// traverse one time and record status
		for (int i = 0; i < ch.length; ++i) {
			if (!map.containsKey(ch[i])) {
				map.put(ch[i], new Rec(i));
			} else {
				Rec cur = map.get(ch[i]);
				cur.cnt++;
				map.put(ch[i], cur);
			}
		}

		// find index of first unique
		int max = Integer.MAX_VALUE;
		for (Map.Entry<Character, Rec> m : map.entrySet()) {
			Rec cur = m.getValue();
			if (cur.cnt == 1 && cur.idx < max) {
				max = cur.idx;
				res = m.getKey();
			}
		}

		return res;
	}
}

class Rec {
	public int cnt;
	int idx;

	public Rec(int i) {
		cnt = 1;
		idx = i;
	}
}
