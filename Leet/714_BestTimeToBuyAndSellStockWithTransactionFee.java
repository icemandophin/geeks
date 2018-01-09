/*
similiar to #188 best time to buy and sell stock IV
try sell and buy op for each day
k is unlimited => single variable is enough
*/
public class Solution {
    public int maxProfit(int[] prices, int fee) {
        // init buy and sell
        int buy = -prices[0];
        int sell = 0;
        int prev;

        for (int i = 1; i < prices.length; ++i) {
            // save prev sell profit for buy op
            prev = sell;
            // just consider fee for each sell op
            sell = Math.max(sell, buy + prices[i] - fee);
            // cur buy is built on prev sell
            buy = Math.max(buy, prev - prices[i]);
        }

        return sell;
    }
}
