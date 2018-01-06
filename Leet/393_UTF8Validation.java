/*
check UTF rules wil bit operation:
1. For 1-byte character, the first bit is a 0, followed by its unicode code.
2. For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
*/
class Solution {
    public boolean validUtf8(int[] data) {
        // mark number of expected remaining parts
        int cnt = 0;
        // check 1st part and remaining separately
        for (int d : data) {
            if (cnt == 0) {
                if (d >> 3 == 0b11110) {
                    cnt = 3;
                } else if (d >> 4 == 0b1110) {
                    cnt = 2;
                } else if (d >> 5 == 0b110) {
                    cnt = 1;
                } else if (d >> 7 == 0b0) {
                    continue;
                } else {
                    return false;
                }
            } else {
                // check remaining part format
                if (d >> 6 != 0b10) {
                    return false;
                }
                cnt--;
            }
        }
        // check cnt value matches
        return cnt == 0;
    }
}
