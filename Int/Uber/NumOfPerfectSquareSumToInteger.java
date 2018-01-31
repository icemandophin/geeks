// Any positive integer can be represented as a sum of squares of other integers. Example:
// 1 = 1^2
// 10 = 3^2 + 1^2
// 13 = 3^2 + 2^2

// There are multiple solutions for these integers:
// 9 = 3^2
// 9 = 2^2 + 2^2 + 1^2
// and so on

// Write a function that given a positive integer returns the minimum squares needed:
// eg:
// Input Output Expl.
// 9	  1      9 = 3^2
// checkSquare(4) - true
// checkSquare(3) - false
// sqrt(5) -> 2.XX
// sqrt(4) -> 2
// floor(sqrt(5)) * floor(sqrt(5)) == 5 -> false
// floor(sqrt(4)) * floor(sqrt(4)) == 4 -> true

import java.util.*;

public class Solution {
    public static int getNumber(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (checkSquare(n)) {
            return 1;
        }

        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; ++i) {
            dp[i] = i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j * j <= i; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(getNumber(72));
    }
}
