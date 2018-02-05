/*
left->right find 1st a[i] > a[i - 1]
then swap with 1st a[j] where j < i and a[j] > a[i]
*/
class Solution {
    public int maximumSwap(int a) {
        // int => str => ch[]
        char[] d = String.valueOf(a).toCharArray();
        // bucket stores largest index of 0-9
        int[] idx = new int[10];
        for (int i = 0; i < d.length; ++i) {
            idx[d[i] - '0'] = i; 
        }

        for (int i = 0; i < d.length; ++i) {
            // check bucket large to small
            for (int j = 9; j > d[i] - '0'; --j) {
                if (idx[j] > i) {
                    char tmp = d[i];
                    d[i] = d[idx[j]];
                    d[idx[j]] = tmp;

                    return Integer.parseInt(String.valueOf(d));
                }
            }
        }

        return a;
    }
}
