/*
check if there is subarray in a[] that sums to target

catepillar approach:
move end forward until sum >= target
then move top forward
*/
public class Solution {
    public boolean subarraySum(int[] a, int x) {
        if (a == null || a.length == 0) {
            return false;
        }

        int j = 0;
        int sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += a[i];
            while (i < a.length && sum >= x) {
                if (sum == x) {
                    return true;
                }
                sum -= a[j++];
            }
        }

        return false;
    }
}

/*
hashset approach:
record presum[i] and store in hashset
if there exist sum[i] - sum[j] == target
=> sum[j] = sum[i] - target
*/
public class Solution {
    public boolean subarray_sum(int[] array, int t) {
        if (array == null || array.length == 0) {
            return false;
        }

        HashSet<Integer> rec = new HashSet<Integer>();
        rec.add(0);
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];

            if (rec.contains(sum - t)) {
                return true;
            }
            else {
                rec.add(sum);
            }
        }

        return false;
    }
