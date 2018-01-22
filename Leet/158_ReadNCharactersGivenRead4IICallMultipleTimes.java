/*
read multiple times => if 1st read num n1 % 4 != 0
then unused char (4 - n1 % 4) need to be buffered for next read
e.g: n1 = 3, n2 = 5 then save last char of 1st read4() and use it
as top char of 2nd result
*/
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    // buffer to save remaining chars between 2 read() calls
    LinkedList<Character> buff = new LinkedList<Character>();

    public int read(char[] buf, int n) {
        // records number of chars copied to buf so far
        int total = 0;
        while (true) {
            // local buffer for each read4() result
            char[] temp = new char[4];
            // in records how many chars needed to copy from input to buf
            // input source size can < 4 => in < 4
            int in = read4(temp);
            // copy read result to intermediate buffer
            for (int i = 0; i < in; i++) {
                buff.add(temp[i]);
            }
            // decide how many chars are needed to be copied from buff to buf
            // n - total is reamining number to be copy to buf
            in = Math.min(n - total, buff.size());
            for (int i = 0; i < in; i++) {
                // write to output
                // remaining of chars in buff will be saved for next call
                buf[total++] = buff.poll();
            }
            // loop ends when no more needed
            if (in == 0) {
                break;
            }
        }

        return total;
    }
}
