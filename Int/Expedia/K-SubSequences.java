package solution;
import java.lang.management.ManagementPermission;
import java.util.*;
// System.out.println("res: ");

import javax.imageio.ImageTypeSpecifier;

public class Solution {
	public static void main(String[] args) {
		int[] a = new int[] {4, 5, 0, -2, -3, 1};
		int k = 5;
		System.out.println(kSub(a, k));
	}

	static long kSub(int[] a, int k) {
		long res = 0;
		long[] mod = new long[k];

		int sum = 0;
		for (int i = 0; i < a.length; ++i) {
			sum += a[i];
			int idx = ((sum % k) + k) % k;
			mod[idx]++;
		}

		for (int i = 0; i < k; ++i) {
			long cur = mod[i];
			if (cur > 1) {
				res += (cur * (cur - 1)) / 2;

			}
		}
		res += mod[0];

		return res;
	}
}
