/*
simple approach: as long as p[i+1] > p[i]
buy in at day i and sell at day i+1, otherwise no buy and sell
this will catch all increase diff
*/
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1]) {
                // good to take action
                res += prices[i] - prices[i - 1];
            }
        }

        return res;
    }
}
