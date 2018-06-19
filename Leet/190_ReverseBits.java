/*
Optimized for performance:
for frequent call scenario, there will be lots of repeated bytes incoming
=> cache each byte and its converted value for fast response
*/
public class Solution {
    // cache each byte pattern and its reversed value
    private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();

    public int reverseBits(int n) {
        byte[] buf = new byte[4];
        for (int i = 0; i < 4; ++i) {
            buf[i] = (byte)((n >>> 8 * i) & 0xFF);
        }
        int res = 0;
        // reverse each byte
        for (int i = 0; i < 4; ++i) {
            res += revByte(buf[i]);
            if (i < 3) {
                res <<= 8;
            }
        }

        return res;
    }

    private int revByte(byte n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int res = 0;
        for (int i = 0; i < 8; ++i) {
            res += ((n >>> i) & 1);

            if (i < 7) {
                res <<= 1;
            }
        }
        // cache new reversed byte result
        cache.put(n, res);

        return res;
    }
}

/*
simple approach: get last bit each time and append to res in reverse order
notice: use unsigned shift to avoid adding 1s on left
*/
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;

        for (int i = 0; i < 32; ++i) {
            res = (res << 1) + (n & 1);
            n >>>= 1;
        }

        return res;
    }
}
