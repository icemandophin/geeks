/*
buffer content and size for each read4 operation
stop reading when buffer empty => check cnt != 0
keep total read size => also return when total == n
*/
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] tmp = new char[4];
        int total = 0;

        while (total < n) {
            int cnt = read4(tmp);

            if (cnt == 0) {
                return total;
            }

            int i = 0;
            while (total < n && i < cnt) {
                buf[total++] = tmp[i++];
            }
        }

        return total;
    }
}