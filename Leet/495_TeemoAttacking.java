/*
for interval diff = a[i] - a[i-1]:
if diff >= duration => poison lasts for one full duration
else poison lasts during the diff/interval

add one additional duration for last attack spot
*/
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < timeSeries.length; ++i) {
            int diff = timeSeries[i] - timeSeries[i - 1];
            res += Math.min(diff, duration);
        }
        // add one for last spot
        res += duration;

        return res;
    }
}
