/*
subarray => two pointer approach: O(N*N)
check sum[i, j] % k
corner scenario: k == 0?
*/
class Solution {
    public boolean checkSubarraySum(int[] a, int k) {
        if (a == null || a.length == 0) {
            return false;
        }
        int n = a.length;
        int i, j;
        int cur;
        for (i = 0; i < n - 1; ++i) {
            cur = a[i];
            for (j = i + 1; j < n; ++j) {
                // get sum{i, j} in cur
                cur += a[j];
                if (cur == k) {
                    // this takes care of sum == k == 0 scenario
                    return true;
                }
                if (k != 0 && cur % k == 0) {
                    // this takes care of k!=0 scenario
                    return true;
                }
            }
        }
        // no match found
        return false;
    }
}

/*
mod approach: O(N)
sum[0, j] % k == sum[0, i-1] % k => sum[i, j] & k == 0
quick find if sum[] % k exist before => build hashmap{sum % k, index}
if prev index exist and curIndex - prevIndex > 1 it is valid pair
Corner scenario:
a = [0, 0], k = 0 => need insert (0, -1) into hash
*/
class Solution {
    public boolean checkSubarraySum(int[] a, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // insert (0, -1) to handle a = [0, 0] and k = 0
        map.put(0, -1);
        int cur = 0;
        int mod;
        // define as integer to include null scenario
        Integer find;
        for (int i = 0; i < a.length; ++i) {
            cur += a[i];
            if (k != 0) {
                // get mod of current sum
                mod = cur % k;
            }
            else {
                mod = cur;
            }
            find = map.get(mod);
            if (find != null) {
                if (i - find > 1) {
                    // find valid prev j
                    return true;
                }
            }
            else {
                // insert unique mod pair
                map.put(mod, i);
            }
        }
        return false;
    }
}
