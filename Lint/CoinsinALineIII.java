/*
check bigger of A and B => diff(A, B) > 0 => diff DP with memory search:
dp[i][j] is max diff value between 1st and 2nd player in [i : j]
if dp[0][n-1] > 0 => 1st win, else 2nd win
for dp[i][j]
1st take a[i] => for 2nd in [i+1 : j]diff(B-A) = dp[i+1][j]
=> diff(A-B) = -dp[i+1][j]
=> for 1st in [i : j] diff(A-B) = a[i] - dp[i+1][j]
1st take a[j] => diff(A-B) = a[j] - dp[i][j-1]
overall: dp[i][j] = Max{a[i] - dp[i+1][j], a[j] - dp[i][j-1]}
Use memory search instead of dp[i][j] in status transition 
*/
import java.util.*;
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] a) {
        int n = a.length;
        // corner scenario
        if (n == 0) {
            return false;
        }
        // flag matrix for memory search
        boolean[][] flag = new boolean[n+1][n+1];
        // dp[i][j] represent max diff value between 1st and 2nd player
        // for subarray [i : j]
        int[][] dp = new int[n+1][n+1];
        // recursive memorial search: O(N)
        // if diff between 1st and 2nd among entire array are > 0 => 1st win
        // else 2nd win
        return (memorySearch(a, flag, dp, 0, n-1) > 0);
    }

    private int memorySearch(int[] a, boolean[][] flag, int[][] dp, int left, int right) {
        // directly return cached result
        if (flag[left][right] == true) {
            return dp[left][right];
        }
        // set flag for [left : right]
        flag[left][right] = true;
        if (left > right) {
            dp[left][right] = 0;
        }
        else if (left == right) {
            dp[left][right] = a[left];
        }
        else if (left+1 == right) {
            dp[left][right] = Math.max(a[left], a[right]) - Math.min(a[left], a[right]);
        }
        else {
            // notice: need to use memorySearch() instead of dp[][]
            // in order to calculate correctly
            dp[left][right] = Math.max(a[left] - memorySearch(a, flag, dp, left+1, right),
                                       a[right] - memorySearch(a, flag, dp, left, right-1));
        }

        return dp[left][right];
    }

}

/*
similiar approach as Coins in a line II:
let dp[i][j] represent max value 1st player can get from subarray[i:j]
1. 1st take a[i]: 2nd take a[i+1]/a[j] => 1st then get dp[i+2][j]/dp[i+1][j-1]
2nd make choice depending on which one can make 1st get less remaining
=> 1st can only get smaller one of the two: Min{dp[i+2][j], dp[i+1][j-1]}
2. 1st take a[j]: 2nd take a[i]/a[j-1] by similiar approach
=> 1st then get Min{dp[i+1][j-1], dp[i][j-2]}
1st will take the bigger option among above:
dp[i][j] = Max{Min{dp[i+2][j], dp[i+1][j-1]} + a[i],
               Min{dp[i+1][j-1], dp[i][j-2]} + a[j]}
*/
import java.util.*;
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        int n = values.length;
        int [][]dp = new int[n + 1][n + 1];
        boolean [][]flag =new boolean[n + 1][n + 1];
        int allsum = 0;
        for(int value : values) {
            allsum += value;
        }

        return allsum < 2*MemorySearch(0,values.length - 1, dp, flag, values);
    }
    int MemorySearch(int left, int right, int [][]dp, boolean [][]flag, int []values) {
        if(flag[left][right])
            return dp[left][right];

        flag[left][right] = true;
        if(left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if(left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            dp[left][right] = Math.max(
                Math.min(MemorySearch(left+2, right, dp, flag,values) , MemorySearch(left+1, right-1, dp, flag, values)) + values[left],
                Math.min(MemorySearch(left+1, right-1, dp, flag, values), MemorySearch(left, right-2, dp, flag, values)) + values[right]);
        }
        return dp[left][right];
    }
}

/*
let dp[i][j] be max value 1st player can get when [i, j] coins are left
when there is segment DP question, always try:
1. think dp[i][j] => dp[i-1][j]/dp[i][j-1]/dp[i-1][j-1] (usually with a[i][j])
instead of thinking small => large
2. optimize with memory search to avoid repeated computing
3. preprocessing some helper data (like sum[i])

coins can be taken from top/end: cannot just take bigger one(greedy)...
=> need to take the choice that can minimize 2nd player's gain in following turn
dp[i][j] = sum[i][j] - min(dp[i+1][j], dp[i][j-1])
         {total value}  {2nd gain when take left} {2nd gain when take right}
*/
import java.util.*;
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        int n = values.length;
        int [][]dp = new int[n + 1][n + 1];
        boolean [][]flag =new boolean[n + 1][n + 1];
        int[][] sum = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum[i][j] = i == j ? values[j] : sum[i][j-1] + values[j];
            }
        }
        int allsum = 0;
        for(int value : values) {
            allsum += value;
        }

        return allsum < 2*MemorySearch(0,values.length - 1, dp, flag, values, sum);
    }
    int MemorySearch(int left, int right, int [][]dp, boolean [][]flag, int []values, int [][]sum) {
        if(flag[left][right])
            return dp[left][right];

        flag[left][right] = true;
        if(left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if(left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int cur = Math.min(MemorySearch(left+1, right, dp, flag, values, sum), MemorySearch(left,right-1, dp, flag, values, sum));
            dp[left][right] = sum[left][right] - cur;
        }
        return dp[left][right];
    }
}
