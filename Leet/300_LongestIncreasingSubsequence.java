/*
DP approach: O(N*N)
dp[i] represent LIS ending with i
dp[i] = max{dp[j] + 1}, where 0<=j<i and a[i]>a[j]
*/
class Solution {
    public int lengthOfLIS(int[] a) {
        if (a == null) {
            return 0;
        }
        int N = a.length;
        if (N <= 1) {
            return N;
        }
        int[] dp = new int[N];
        int i, j;
        int res = 0;
        // init dp[i]
        for (i = 0; i < N; ++i) {
            dp[i] = 1;
        }
        // traverse array, for each a[i]
        // get max dp for all previous a[j] that smaller than a[i]
        // final value of dp[i] = max{dp[j]}+1
        for (i = 1; i < N; ++i) {
            for (j = 0; j < i; ++j) {
                if (a[i] > a[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            // dp[i] is finished, update final result
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

/*
Optimize with Binary Search: O(NLogN)
build an increasing array b[N], end element is current max element
traverse the array, for each new element a[i]:
if a[i] > end, insert it and it becomes new end/max element
else binary search 1st b[j] >= a[i], replace it with a[i]
*/
class Solution {
    public int lengthOfLIS(int[] a) {
        if (a == null) {
            return 0;
        }
        int N = a.length;
        if (N <= 1) {
            return N;
        }
        // use list to hold dynamic increasing array
        ArrayList<Integer> b = new ArrayList<Integer>();

        for (int i = 0; i < N; ++i) {
            if ((b.size() == 0) || (a[i] > b.get(b.size() - 1))) {
                // a[i] can make current LIS grow, make it new end/max element
                b.add(a[i]);
            }
            else {
                // need to replace one of exising elements in list with smaller a[i]
                // notice: current elements in list is NOT LIS, but every element in list
                // does indicate that there exist an element in a[N] which can take its place and
                // make LIS with the same length
                // we binary replace the smaller one into list to avoid prev larger elements stay in
                // list and block growth of LIS with new a[i]
                int j = BinarySearch(b, 0, b.size() - 1, a[i]);
                // replace original element with a[i]
                // after replacement, it lowers the bar for new element x that a[i] < x < previous a[j]
                // to be added to LIS => more chance to grow in size
                b.set(j, a[i]);
            }
        }
        return b.size();
    }
    // find 1st index in list a that a.get(index) > x
    private int BinarySearch(ArrayList<Integer> a, int top, int end, int x) {
        while (top < end) {
            int mid = top + (end - top) / 2;
            if (a.get(mid) < x) {
                top = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return end;
    }
}
