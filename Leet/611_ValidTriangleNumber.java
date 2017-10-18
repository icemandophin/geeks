/*
two pointer approach: O(N*N)
traverse max edge a[k]
i, j refer to current start/end
move i, j depending on a[i] + a[j] value
*/
class Solution {
    public int triangleNumber(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length;
        int res = 0;
        int i, j, k;
        // sort array
        Arrays.sort(a);
        // mark current max value as k
        for (k = n - 1; k >= 2; k--) {
            i = 0;
            j = k - 1;
            while (i < j) {
                if (a[i] + a[j] > a[k]) {
                    // all elements in [i, j] makes triangle a[x], a[j], a[k]
                    res += j - i;
                    // try smaller a[j]
                    j--;
                }
                else {
                    // try alrger a[i]
                    i++;
                }
            }
        }
        return res;
    }
}
