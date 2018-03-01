/*
make i - j <= k => keep tree set size to k by removing elements with index < j - k
check a[x] - a[y] <= t => get floor/ceil of a[x] in tree set (log(k)) and compare
*/
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] a, int k, int t) {
        TreeSet<Integer> tree = new TreeSet<>();
        // for each elemengt, find closet values in tree set
        for (int i = 0; i < a.length; ++i) {
            // get min element that >= given value
            Integer ceil = tree.ceiling(a[i]);
            // Notice: use ceil - t to avoid type flow
            if (ceil != null && ceil - t <=a[i]) {
                return true;
            }
            // get max element that <= a[i]
            Integer floor = tree.floor(a[i]);
            if (floor != null && a[i] <= t + floor) {
                return true;
            }
            // add AFTER check
            tree.add(a[i]);
            // control tree set size to k
            // if exceed => remove element with smaller index
            if (tree.size() > k) {
                tree.remove(a[i - k]);
            }
        }

        return false;
    }
}
