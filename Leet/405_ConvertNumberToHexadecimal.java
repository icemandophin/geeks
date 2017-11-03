/*
bit shift approach
natually cover n < 0 scenario
*/
class Solution {
    public String toHex(int num) {
        if (num == 0) {
            // no need to convert
            return "0";
        }
        // build binary to hex mapping
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        // convert 4 bits to hex digit each time
        String res = "";
        int cnt = 0;
        while (num != 0 && cnt++ < 8) {
            // get last 4 bits and add to LEFT
            res = map[num & 0xf] + res;
            // UNSIGNED right shift, add 0 from right side even for negative num
            num = num >>> 4;
        }
        return res;
    }
}
