/*
greedy approach:
update max distance player can cover during move
if cover > cur i => player can keep moving forward
if i == n => reach dest
if cover < cur i => no way to reach i and following position
*/
class Solution {
    public boolean canJump(int[] a) {
        // records max distance that can move from start
        int cover = 0;

        for (int i = 0; i < a.length; ++i) {
            if (i > cover) {
                // impossible to reach i
                return false;
            }

            cover = Math.max(cover, i + a[i]);
        }

        return true;
    }
}
