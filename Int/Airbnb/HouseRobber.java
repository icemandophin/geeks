/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing
 * each of them is that adjacent houses have security system connected and it will automatically
 * contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * ALSO, print out the houses you choose to reach the maximum.
 */

import java.util.Arrays;

public class HouseRobber {
  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      System.out.println("Houses: 0");
      return nums[0];
    }

    int[] dp = new int[nums.length];
    int[] prev = new int[nums.length];
    Arrays.fill(prev, -1);

    dp[0] = nums[0];
    prev[0] = 0;
    dp[1] = Math.max(nums[0], nums[1]);
    prev[1] = dp[1] == nums[1] ? 1 : -1;


    for (int i = 2; i < nums.length; i++) {
      int robCur = nums[i] + dp[i - 2];
      if (robCur > dp[i - 1]) {
        dp[i] = robCur;
        prev[i] = i - 2;
      } else {
        dp[i] = dp[i - 1];
      }
    }

    int index = nums.length - 1;
    while (prev[index] != index) {
      if (prev[index] == -1) {
        index--;
      } else {
        System.out.print(index + " ");
        index = prev[index];
      }
    }
    System.out.println(index);

    return dp[nums.length - 1];
  }
}

//class Main {
//  public static void main(String[] args) {
//    HouseRobber hr = new HouseRobber();
//    int[] nums1 = {3, 5, 6, 1, 2, 4, 1};
//    System.out.println(hr.rob(nums1));
//    int[] nums2 = {5, 2, 4, 7, 3, 1};
//    System.out.println(hr.rob(nums2));
//  }
//}
