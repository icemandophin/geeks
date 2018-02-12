/*
no division => build pre-product left[] and right[]
=> res[i] = left[i] * right[i]
*/
class Solution {
    public int[] productExceptSelf(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        // init start of left[] and right[] as 1
        left[0] = 1;
        right[n - 1] = 1;

        for (int i = 0; i < n - 1; ++i) {
            left[i + 1] = left[i] * a[i];
        }

        for (int j = n - 1; j > 0; --j) {
            right[j - 1] = right[j] * a[j];
        }

        for (int i = 0; i < n; ++i) {
            res[i] = left[i] * right[i];
        }

        return res;
    }
}

/*
Optimize space:
save left[] to res[]
replace right[] with cur pre-product right
*/
class Solution {
    public int[] productExceptSelf(int[] a) {
        int n = a.length;
        int[] res = new int[n];

        res[0] = 1;
        int right = 1;
        // build left[]
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] * a[i - 1];
        }
        // add right[]
        for (int i = n - 2; i >= 0; --i) {
            right *= a[i + 1];
            res[i] *= right;
        }

        return res;
    }
}
