/*
Devide and Conquer + DP: O(N) time and O(N) space
easy to calculate max profit for 1 transaction
=> patition array with i and get max profit for [0, i] and [i, n-1] then get max sum

eg:  a = 1 4 5 7 6 3 2 9
   left: 0 3 4 6 6 6 6 8  max 1-trans profit before day[i]
  right: 8 7 7 7 7 7 7 0  max 1-trans profit after day[i]
max sum:         13
*/
class Solution {
    public int maxProfit(int[] p) {
        if (p == null || p.length == 0) {
            return 0;
        }
        int n = p.length;
        int res = 0;
        // create dp for both sides
        int[] left = new int[n];
        int[] right = new int[n];
        // lowest day to buy on left
        int buy = p[0];
        // highest day to sell on right
        int sell = p[n - 1];
        // fill left dp
        for (int i = 1; i < n; ++i) {
            buy = Math.min(buy, p[i]);
            // inherit prev max profit if new one is smaller
            left[i] = Math.max(left[i - 1], p[i] - buy);
        }
        // fill right one
        for (int i = n - 2; i >= 0; --i) {
            sell = Math.max(sell, p[i]);
            right[i] = Math.max(right[i + 1], sell - p[i]);
        }
        // summarize max profit
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, left[i] + right[i]);
        }
        return res;
    }
}

/*
Optimize - Ceiling Method: O(N) time and O(1) Space
for each day we can buy/sell once/twice
=> test buying or selling stocks at each day and update
below 4 status to set it to the maximum value:
buy1: we bought the stock as the beginning of the first transaction.
Our profit would be the initial value 0 – prices[i] (negative indicate spend)
sell1: we sell the stock in the first transaction.
Our profits would be buy1 + prices[i]
buy2: we bought the stock as the beginning of the second transaction.
our profit would be sell1 – prices[i]
sell2: we sell the stack in the second transaction.
our profits would now be buy2 + prices[i].

Note: the update order of the four values is determined:
sell2 - buy2 - sell1 - buy1
because we cannot sell before buy the 1st or 2nd stocks

eg:
Prices      3    1    2    8    3    1    9    6
release2    0    0    1    7    7    7    1    1
hold2      -3   -1   -1   -1    4    6    1    1
release1    0    0    1    7    7    7    1    1
hold1      -3   -1   -1   -1   -1   -1    3    3
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // init buy/sell 1/2 value
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;
        // try 4 ops for each day
        for (int p : prices) {
            // each records cur profit after operation
            sell2 = Math.max(sell2, buy2 + p);
            buy2 = Math.max(buy2, sell1 - p);
            sell1 = Math.max(sell1, buy1 + p);
            buy1 = Math.max(buy1, -p);
        }

        return sell2;
    }
}
