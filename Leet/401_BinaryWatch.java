/*
list all times 00:00 - 11:59 and check
bitCount() count # of 1s in value
*/
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 60; ++j) {
                if (Integer.bitCount((i << 6) + j) == num) {
                    res.add(String.format("%d:%02d", i, j));
                }
            }
        }

        return res;
    }
}
