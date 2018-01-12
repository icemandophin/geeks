/*
repeated calculate digit square sum => util method
detect infinite loop => record calculated number in HashSet
if repeat then there exist infinite loop 
*/
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int cur = n;
        while (cur != 1) {
            cur = digitSquare(cur);
            if (!set.contains(cur)) {
                set.add(cur);
            } else {
                break;
            }
        }

        if (cur == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int digitSquare(int n) {
        int res = 0;

        while (n != 0) {
            int d = n % 10;
            res += d * d;
            n /= 10;
        }

        return res;
    }
}
