/*
DP approach: time O(Nk) space O(Nk) => whether last trans is completed on last day
diff = p[i] - p[i-1]
max profit for day[0 : i] with j-th trans completed at day[i]
local[i][j] = max{local[i-1][j-1] + max(0, diff), local[i-1][j] + diff}
第i天正好完成第j笔交易的最大收益，可以基于第i-1天正好完成第j-1笔交易的最大收益加上当天交易的差值，
还有第i-1天正好完成第j笔交易的最大收益加上当天交易的差值。
第i-1天正好完成第j-1笔交易情况:
当前交易的差值取0和实际昨天今天差价中较大的，因为我们还有一次自由交易的余地，
所以如果亏的话完全可以当天买卖避免损失。
但第i-1天正好完成第j笔交易这种情况来推导第i天正好完成第j笔交易时，相当于第i天已经要连着第i-1天交易，
使得第i-1天正好完成的第j笔交易和第i天正好完成的第j笔交易是同一个交易

max profit for day[0 : i] with at most j trans:
global[i][j] = max{global[i-1][j], local[i][j]}
第i天已经执行j笔交易的最大收益，可以基于第i-1天已经执行j笔交易的最大收益和
第i天正好完成第j笔交易的最大收益

Notice: when k > N/2 => for k - N/2 part buy and sell happen in same day, no profit
=> equal to no limit to op times => become 122_BestTimeToBuyAndSellStockII.java
*/
public class Solution {
    public int maxProfit(int k, int[] prices) {
        // speedup when K > N / 2
        if (k > prices.length / 2) {
            int sum = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i-1]) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }
        // init dp[][]
        int[][] global = new int[prices.length][k + 1];
        int[][] local = new int[prices.length][k + 1];
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for(int j = 1; j < k + 1; j++) {
                // update local dp
                local[i][j] = Math.max(global[i - 1][j - 1]+Math.max(0, diff), local[i - 1][j]+diff);
                // update global dp
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[prices.length - 1][k];
    }
}

/*
similiar optimization like 123_BestTimeToBuyAndSellStockIII.java
time: O(N) space: O(k)
*/
public class Solution {
    public int maxProfit(int k, int[] prices) {
        // speedup when K > N / 2
        if (k > prices.length / 2) {
            int sum = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i]>prices[i-1]) {
                    sum += prices[i] - prices[i-1];
                }
            }
            return sum;
        }
        // record cur max profit after buy/sell at day[i]
        int[] sell = new int[k+1];
        int[] buy = new int[k+1];
        for (int i = 0; i < k+1; i++) {
            buy[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j < k+1; j++) {
                // cur max profit after sell at day[d]
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
                // cur max profit after buy at day[d]
                buy[j] = Math.max(buy[j], sell[j-1] - prices[i]);
            }
        }
        return sell[k];
    }
}
