public class Solution {
    // delete k digits from given number and make remaining max
    public int deleteNumbers(String s, int k) {
        int res = 0;
        int i = 0;
        int n = s.length();
        char[] a = s.toCharArray();

        while (i < n - 1 && a[] >= a[]) {
            i++;
        }
        if (i == n - 1) {
            n--;
            // s is descending
            while (k--) {
                n--;
            }
            break;
        } else {
            for (int j = i; j < n - 1; ++j) {
                a[j] = a[j + 1];
            }
            n--;
        }

        for (int x = 0; x < n; ++x) {
            res = res * 10 + x;
        }

        return res;
    }
}
