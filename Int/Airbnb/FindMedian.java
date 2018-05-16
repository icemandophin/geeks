/**
 * Find the median from a large file of integers. You can not access the numbers by index, can
 * only access it sequentially. And the numbers cannot fit in memory.
 *
 * Solution
 * Integers range from Integer.MIN_VALUE to Integer.MAX_VALUE. We can use rang-based binary search
 * upper bound: large
 * lower bound: small
 * find kth smallest element, k == len / 2 + 1 for median (odd)
 * guess: (small + large) / 2
 * count how many numbers less or equal to guess
 *    if count == k : return largest (res) that is less or equal to guess
 *    if count < k: search for res + 1 to large
 *    if count > k: search for small to res
 *
 * Maximum scanning time is 32
 */
public class FindMedian {
  public double findMedian(int[] nums) {
    int len = 0;
    for (int num : nums) {
      len++;
    }

    if (len % 2 == 1) {
      return (double)search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    } else {
      return (double)(search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) +
              search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
    }
  }

  private long search(int[] nums, int k, long small, long large) {
    if (small == large) {
      return small;
    }

    long res = small;
    long guess = (small + large) / 2;
    int count = 0;
    for (int num : nums) {
      if (num <= guess) {
        count++;
        res = Math.max(res, num);
      }
    }

    if (count == k) {
      return res;
    } else if (count < k) {
      return search(nums, k, Math.max(res + 1, guess), large);
    } else {
      return search(nums, k, small, res);
    }
  }
}

//class Main {
//  public static void main(String[] args) {
//    FindMedian fm = new FindMedian();
//    System.out.println(fm.findMedian(new int[] {-6, 18, 9}));
//    System.out.println(fm.findMedian(new int[] {-20, 0, 7, 5}));
//    System.out.println(fm.findMedian(new int[] {1, 2, 2, 2, 3, 3}));
//  }
//}
