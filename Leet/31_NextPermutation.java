/*
if a[] descending => lexicographically max => need to reverse entire array as reset
otherwise:
1. find last ascending node X from RIGHT
2. find 1st node Y that bigger than X from RIGHT
3. swap them and reverse [X : N] to make the min lexicographically larger permutation
*/
class Solution {
    public void nextPermutation(int[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // find first a[j] that > ap[i]
            int j = a.length - 1;
            while (j >= 0 && a[i] >= a[j]) {
                j--;
            }

            swap(a, i, j);
        }
        // revert descending array to ascending
        reverse(a, i + 1);
    }
    // reverse subsrray starting from idx x
    private void reverse(int[] a, int x) {
        int i = x;
        int j = a.length - 1;

        while (i < j) {
            swap(a, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] a, int i , int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
