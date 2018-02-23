import java.util.*;
// System.out.println("res: ");

import javax.activation.MailcapCommandMap;

public class Solution {
	public static void main(String[] args) {
		int arr[] = {1, 2, 2, 3, 3, 3, 3};
		int x = 3;
		CountFreq obj = new CountFreq();

		System.out.println(obj.count(arr, x));
	}
}

class CountFreq {
	public int count(int[] a, int x) {
		int res = -1;
		int n = a.length;

		int first = findFirst(a, x, 0, n - 1);
		if (first == -1) {
			return -1;
		}

		int last = findLast(a, x, first, n - 1);
		res = last - first + 1;

		return res;
	}

	private int findFirst(int[] a, int x, int top, int end) {
		int n = a.length;

		while (top <= end) {
			int mid = top + (end - top) / 2;
			// condition for first instance of x
			// hit start or prev one is smaller
			if ((mid == 0 || x > a[mid - 1]) && a[mid] == x) {
				return mid;
			} else if (x > a[mid]) {
				top = mid + 1;
			} else {
				// decrease end as long as a[mid] == x
				end = mid - 1;
			}
		}

		return -1;
	}

	private int findLast(int[] a, int x, int top, int end) {
		int n = a.length;

		while (top <= end) {
			int mid = top + (end - top) / 2;
			// condition for last instance of x
			// hit end or next one is bigger
			if ((mid == n - 1 || x < a[mid + 1]) && a[mid] == x) {
				return mid;
			} else if (x < a[mid]) {
				end = mid - 1;
			} else {
				// as long as a[mid] == x => increase top
				top = mid + 1;
			}
		}

		return -1;
	}
}
