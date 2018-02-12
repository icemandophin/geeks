/*
cnt_1 == cnt_0
=> regard 0 as -1 then sum == 0
like 2-sum: pre-sum + hashmap
notice sum[0 : i] can be 0
=> add (0 -> -1) to map to avoid error mapping
*/
class Solution {
    public int findMaxLength(int[] a) {
        // record max distance(i, j)
        int res = 0;
        // record pre-sum
        int sum = 0;
        // map sum -> index
        Map<Integer, Integer> map = new HashMap<>();
        // if sum[i] == 0 => required len is i - (-1)
        map.put(0, -1);

        for (int i = 0; i < a.length; ++i) {
            if (a[i] == 0) {
                sum -= 1;
            } else {
                sum += a[i];
            }
            // always check before add
            if (map.containsKey(sum)) {
                int j = map.get(sum);
                // do not insert for when sum exist
                // to maximize len diff
                // i always bigger than j
                res = Math.max(res, i - j);
            } else {
                map.put(sum, i);
            }
        }

        return res;
    }
}
