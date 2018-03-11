class Solution {
    public int[] twoSum(int[] a, int x) {
        int[] res = new int[2];
        int n = a.length;
        int i = 0;
        int j = n - 1;

        while (i <= j) {
            int cur = a[i] + a[j];
            if (cur == x) {
                res[0] = i + 1;
                res[1] = j + 1;

                return res;
            } else if (cur < x) {
                i++;
            } else {
                j--;
            }
        }

        return res;
    }
}
