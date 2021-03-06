/*
random select value from v[] based on weight w[]
regard [1 : sum{w[]}] as interval
each w[i] cut it to buckets
find which one rand number belongs to
*/
public class Solution {
    public int weightRandomNumber(int[] v, int[] w) {
        int total = 0;
        for (int i = 0; i < w.length; ++i) {
            total += w[i];
        }
        Random rand = new Random();
        int num = rand.nextInt(total) + 1;
        for (int i = 0;  i < v.length; ++i) {
            num -= v[i];
            if (num <= 0) {
                break;
            }
        }

        return v[i];
    }
}
