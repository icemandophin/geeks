/*
Need to know Sum(A) and Sum(B) who is bigger => build dp[] based on difference
dp[i] represent max value difference between first and second player/taker
for subarray start from a[i] => dp[0] is the final/sum diff between 1st and 2nd player
*/
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        int n = values.length;

        if (n == 0) {
            return false;
        }

        if (n <= 2) {
            return true;
        }

        int[] dp = new int[n];
        // when there is 1/2 coins left, first player takes all
        dp[n - 1] = values[n - 1];
        dp[n - 2] = values[n - 1] + values[n - 2];

        // if A take 1 coin, for subarray[i+1 - n-1]: B - A = dp[i+1]
        // hence total diff of [i - n-1]: a[i] + (A-B) = a[i] - dp[i+1]
        for (int i = n - 3; i >= 0; i--) {
            dp[i] = Math.max(values[i] - dp[i + 1], values[i] + values[i + 1] - dp[i + 2]);
        }

        return dp[0] > 0;
    }
}

 /*
 total sum is fixed => 1st get more, 2nd get less remaining
 when 1st and 2nd make choice, they always make opponent get less from remaining after current selection
 let dp[i] represent max value 1st player can get from coin i to end/N-1
 consider ending:
 i = N-1: take last coin - dp[i] = A[N-1]
 i = N-2: take last 2 - dp[i] = A[N-1] + A[N-2]
 i = N-3: take most values/both coins and left 1:
          dp[i] = A[N-3] + A[N-2]
 consider general scenarios:
 when 1st player face coin i to N-1:
 1. take a[i]: then 2nd can take
 (1) a[i+1]: 1st take most from i+2 to end - dp[i+2]
 (2) a[i+1]+[i+2]: 1st take from i+3 - dp[i+3]
 2nd player always want to take most, hence 1st player get the "bad" scenario: a[i] + min{dp[i+2], dp[i+3]}
 2. 1st player take a[i]+a[i+1]: 2nd take
 (1) a[i+2]: dp[i+3]
 (2) a[i+2]+a[i+3]: dp[i+4]
 similiar to above: a[i]+a[i+1] + min{dp[i+3]+dp[i+4]}
 1st player can take the bigger one from "bad" scenario:
 max{(a[i] + min{dp[i+2], dp[i+3]}), (a[i]+a[i+1] + min{dp[i+3]+dp[i+4]})}
 */
public class Solution {
        /*
         * @param values: a vector of integers
         * @return: a boolean which equals to true if the first player will win
         */
        public boolean firstWillWin(int[] values) {
        // write your code here
        int []dp = new int[values.length + 1];
        boolean []flag =new boolean[values.length + 1];
        int sum = 0;
        for(int now : values)
            sum += now;

        return sum < 2*MemorySearch(values.length, dp, flag, values);
    }
    int MemorySearch(int n, int []dp, boolean []flag, int []values) {
        if(flag[n] == true)
            return dp[n];
        flag[n] = true;
        if(n == 0)  {
            dp[n] = 0;
        } else if(n == 1) {
            dp[n] = values[values.length-1];
        } else if(n == 2) {
            dp[n] = values[values.length-1] + values[values.length-2];
        } else if(n == 3){
            dp[n] = values[values.length-2] + values[values.length-3];
        } else {
            dp[n] = Math.max(
                Math.min(MemorySearch(n-2, dp, flag,values) , MemorySearch(n-3, dp, flag, values)) + values[values.length-n],
                Math.min(MemorySearch(n-3, dp, flag, values), MemorySearch(n-4, dp, flag, values)) + values[values.length-n] + values[values.length - n + 1]
                );
        }

        return dp[n];
    }
}
