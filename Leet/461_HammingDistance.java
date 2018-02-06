/*
cnt num of diff binary bits => XOR then count 1
*/
class Solution {
    public int hammingDistance(int x, int y) {
        int res = 0;
        int dif = x ^ y;

        while (dif != 0) {
            res += dif & 1;
            dif >>>= 1;
        }

        return res;
    }
}
