/*
same as #349 - except no need to remove duplicate ones
*/
class Solution {
    public int[] intersect(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = 0;
        int m = a.length;
        int n = b.length;
        // create dynamic list for res
        List<Integer> both = new ArrayList<>();
        // traverse
        while (i < m && j < n) {
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                both.add(a[i]);
                i++;
                j++;
            }
        }

        int[] res = new int[both.size()];
        for (i = 0; i < both.size(); ++i) {
            res[i] = both.get(i);
        }

        return res;
    }
}
