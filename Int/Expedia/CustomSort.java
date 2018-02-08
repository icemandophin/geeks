package solution;
import java.lang.management.ManagementPermission;
import java.util.*;
// System.out.println("res: ");

public class Solution {
	public static void main(String[] args) {
		int[] test = new int[] {8, 5, 5, 5, 5, 1, 1, 1, 4, 4};
		customSort(test);
		for (Integer x : test) {
			System.out.println(x);
		}
	}

	static void customSort(int[] a) {
		if (a == null || a.length == 0) {
			return;
		}

		Map<Integer, Integer> map = new HashMap<>();
		PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
				if (a.getValue() != b.getValue()) {
					return a.getValue() - b.getValue();
				} else {
					return a.getKey() - b.getKey();
				}
			}
		});

		for (int i : a) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			q.offer(entry);
		}

		int j = 0;
		while (!q.isEmpty()) {
			Map.Entry<Integer, Integer> entry = q.poll();
			int val = entry.getKey();
			int cnt = entry.getValue();

			for (int k = 0; k < cnt; ++k) {
				a[j++] = val;
			}
		}
	}
}
