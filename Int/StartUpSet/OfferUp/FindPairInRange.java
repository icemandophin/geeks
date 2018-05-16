/*
int [] nums = 8,3,4,6,7
t = maximum absolute difference allowed between two values;
k = maximum absolute index diff

is there a pair number existing in this intger array with index i, j that math.abs(nums[i] - num[j]) < t, and math.abs( i, j)<  k
*/

import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    int[] nums = {8,3,4,6,7};
    int k = 1;
    int t = 0;

    System.out.println("k = " + k + " t = " + t + " result: " + findPair(nums, t, k));
  }

  public static boolean findPair(int[] a, int t, int k) {
    if (a == null || a.length <= 1) {
      return false;
    }

    int n = a.length;
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    for (int i = 0; i < n; ++i) {
      treeMap.put(a[i], i);
      floorKey = treeMap.floorKey(a[i]);
      if (floorKey != null && Math.abs(floorKey - a[i]) <= t) {
        floor = treeMap.get(floorkey);
        if (Math.abs(floor - i) <= k) {
          return true;
        }
      }

      ceilKey = treeMap.ceilKey(a[i]);
      if (ceilKey != null && Math.abs(ceilKey - a[i]) <= t) {
        ceil = treeMap.get(ceilKey);
        if (Math.abs(ceil - i) <= k) {
          return true;
        }
      }

    }

    return false;
  }
}
