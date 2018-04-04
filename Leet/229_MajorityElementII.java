/*
extended moore voting:
1. there are at most 2 elements appear more than ⌊ n/3 ⌋ times
=> find 2 elements with moore voting (similiar to #169)
2. need to verify if Majority Element exist
=> traverse again and verify elements' counts
*/
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // record candidate elements
        int m1 = 0, m2 = 0;
        // record counts of m1, m2
        int c1 = 0, c2 = 0;
        // find 2 major elements
        for (Integer a : nums) {
            // notice: adding count need to be checked first
            // to handle same val consecutive input like {8, 8, 7, 7, 7}
            if (a == m1) {
                c1++;
            } else if (a == m2) {
                c2++;
            } else if (c1 == 0) {
                m1 = a;
                c1 = 1;
            } else if (c2 == 0) {
                m2 = a;
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }
        // traverse again and verify m1, m2 is majority
        c1 = c2 = 0;
        for (Integer a : nums) {
            if (a == m1) {
                c1++;
            } else if (a == m2) {
                c2++;
            }
        }

        if (c1 > nums.length / 3) {
            res.add(m1);
        }
        if (c2 > nums.length / 3) {
            res.add(m2);
        }

        return res;
    }
}
