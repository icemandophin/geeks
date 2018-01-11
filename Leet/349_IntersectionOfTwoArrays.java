/*
merge sort approach: O(NlogN + MlogM) time O(1) space
1. sort a[] and b[]
2. for each a[i] and b[j] if equal add to res
else add index count of Min{a[i], b[j]}
*/
class Solution {
    public int[] intersection(int[] a, int[] b) {
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
                // should not add repeated elements into res
                // traverse until new value found
                while (i < m && a[i] == a[i - 1]) {
                    i++;
                }
                while (j < n && b[j] == b[j - 1]) {
                    j++;
                }
            }
        }

        int[] res = new int[both.size()];
        for (i = 0; i < both.size(); ++i) {
            res[i] = both.get(i);
        }

        return res;
    }
}

/*
hash set approach: O(N + M) time O(N + M) space
1st set contains unique a[i]
2nd set contains (unique) duplicate element with b[]
*/
class Solution {
    public int[] intersection(int[] a, int[] b) {
        Set<Integer> as = new HashSet<>();
        Set<Integer> both = new HashSet<>();
        // fill set1
        for (int i : a) {
            as.add(i);
        }
        // fill set2 by checking set1
        for (int j : b) {
            if (as.contains(j)) {
                both.add(j);
            }
        }

        int[] res = new int[both.size()];
        int i = 0;
        for (Integer one : both) {
            res[i++] = one;
        }

        return res;
    }
}
