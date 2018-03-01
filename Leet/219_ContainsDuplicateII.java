class Solution {
    public boolean containsNearbyDuplicate(int[] a, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.length; ++i) {
            if (map.containsKey(a[i]) && i - map.get(a[i]) <= k) {
                return true;
            } else {
                // for same value, larger index always inserted later
                map.put(a[i], i);
            }
        }

        return false;
    }
}
