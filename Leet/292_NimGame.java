/*
if n is times of 4, no matter 1, 2 or 3 1st player take,
2nd player can just take 3, 2, 1 and make remaing times of 4 again
1st will always leave some stones after the take for 2nd player
hence 1st have to lose => vice versa
*/
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0 ;
    }
}
