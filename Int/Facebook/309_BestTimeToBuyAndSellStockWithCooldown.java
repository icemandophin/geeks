/*
original buy - sell relationship:
sell[i] = Math.max(sell[i], buy[i] + price);
buy[i] = Math.max(buy[i], sell[i - 1] - price);

with transaction:
buy[i]  = max(rest[i-1]-price, buy[i-1])
sell[i] = max(buy[i-1]+price, sell[i-1])
rest[i] = max(sell[i-1], buy[i-1], rest[i-1])

on day[i] buy in spend extra money, sell get in extra money
buy[i] < = rest[i] < = sell[i] => rest[i] = sell[i-1]
Hence
sell[i] = max(buy[i-1]+price, sell[i-1])
buy[i] = max(sell[i-2]-price, buy[i-1])
only diff is that buy built on sell[i-2]

optimize to time O(1): - rolling variable
*/
public class Solution {
    public int maxProfit(int[] prices) {
        // according to O(n) version, need to save prev day
        // AND the day before prev day sell profit
        int prev1 = 0;
        int prev2 = 0;
        int buy = Integer.MIN_VALUE;
        int prev_buy;
        for (int price : prices) {
            // prev_buy = buy[i - 1]
            prev_buy = buy;
            buy = Math.max(prev2 - price, prev_buy);
            // before below code:
            // prev2 = sell[i - 2]
            // prev1 = sell[i - 1]
            prev2 = prev1;
            prev1 = Math.max(prev_buy + price, prev2);
            // after update
            // prev2 becomes sell[i - 1]
            // prev1 becomes sell[i]
        }
        // after loop exit, prev1 is updated with last day sell profit
        return prev1;
    }
}
