/*
for a[n] = {1, 2, ... n} there are (n - 1)! permutations start with number i
=> calculate factorial 1 - n:
int d = (k - 1) / (n - 1)! + 1 is the 1st digit of kth permutation
d is used => remove it from a[n]
skip prev permutations starting with a[i] => decrease k by (n - 1)! * d
=> continue find k - (n - 1)! * d permutation in remaining n - 1 list
*/
class Solution {
    public String getPermutation(int n, int k) {
        // f[x] = x!
        int[] f = new int[n];
        f[0] = 1;
        // record numbers 1 - n
        List<Integer> d = new ArrayList<>();
        d.add(1);

        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] * i;
            d.add(i + 1);
        }
        // kth permutation refers to k-1 num in list
        k--;
        StringBuilder res = new StringBuilder();

        for (int i = n - 1; i >= 0; --i) {
            int idx = k / f[i];
            res.append(d.get(idx));
            d.remove(idx);
            k -= idx * f[i];
        }

        return res.toString();
    }
}
