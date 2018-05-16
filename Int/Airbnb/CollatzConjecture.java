/**
 * https://en.wikipedia.org/wiki/Collatz_conjecture
 * 考拉兹猜想
 * 给你公式，比如偶数的话除2，奇数的话就变成3*n+1，对于任何一个正数，数学猜想是最终他会变成1。
 * 每变一步步数加1，给一个上限，让找出范围内最长步数
 *
 * 记忆化搜索
 *
 * 这题如果follow up还可以考虑输出最长的序列，那么我们就需要一个map来保存 integer -> list(integer)
 */

import java.util.HashMap;
import java.util.Map;

public class CollatzConjecture {
  public int findLongestSequence(int n) {
    Map<Integer, Integer> cache = new HashMap<>();
    cache.put(1, 1);
    int longest = 0;
    for (int i = 1; i <= n; i++) {
      longest = Math.max(longest, helper(i, 0, cache));
    }

    return longest;
  }

  private int helper(int n, int curLen, Map<Integer, Integer> cache) {
    if (cache.containsKey(n)) {
      return curLen + cache.get(n);
    }

    int len = n % 2 == 0 ? helper(n / 2, curLen + 1, cache) : helper(n * 3 + 1, curLen + 1, cache);
    cache.put(n, len - curLen);
    return len;
  }
}

//class Main {
//  public static void main(String[] args) {
//    CollatzConjecture cc = new CollatzConjecture();
//
//    // 1
//    // 2 -> 1
//    // 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
//    // 4 -> 2 -> 1
//    // 5 -> 16 -> 8 -> 4 -> 2 -> 1
//    // 6 -> 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
//    // 7 -> 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
//    // ...
//
//    for (int i = 1; i <= 7; i++) {
//      System.out.println(cc.findLongestSequence(i));
//    }
//    System.out.println(cc.findLongestSequence(1000));
//  }
//}