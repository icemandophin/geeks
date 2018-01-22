/*
bucket sort approach:
1. count num of papers per citation times
2. from large to small: find 1st time sum of cnt > index
which indicates largest h that satisfy definition
*/
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        // buck[i] counts number of papers that has been citated i times
        // if citation time > n it goes to buck[n]
        int[] buckets = new int[n+1];
        for(int c : citations) {
            if(c >= n) {
                // for a[i] > n go to nth bucket
                buckets[n]++;
            } else {
                // if a[i] < n then add count of buck[a[i]]
                buckets[c]++;
            }
        }
        int count = 0;
        // start from max citation number(n)
        // when sum (total number of papers that citated more than c[idx] times) > idx
        // then that is the H index
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) {
                return i;
            }
        }
        return 0;
    }
}
