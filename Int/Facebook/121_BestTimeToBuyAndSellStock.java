class Solution {
    public int maxProfit(int[] prices) {
        // record max diff between prices[i] and low
        int res = 0;
        int low = Integer.MAX_VALUE;
        for (int p : prices) {
            low = Math.min(low, p);
            // update res with cur diff
            res = Math.max(res, p - low);
        }

        return res;
    }
}
