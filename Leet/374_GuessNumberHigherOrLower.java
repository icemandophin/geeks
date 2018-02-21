/*
plain binary search
*/
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int l = 1;
        int r = n;

        while (l <= r) {
            int m = l + (r - l) / 2;
            int cur = guess(m);

            if (cur == 0) {
                return m;
            } else if (cur < 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return -1;
    }
}
/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */
