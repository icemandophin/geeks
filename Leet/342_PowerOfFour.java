/*
if x = 4^n then x can be divided by 4 until x == 1
*/
public class Solution {
    public boolean isPowerOfFour(int num) {
        if (num <= 0) {
            return false;
        }

        while (num % 4 == 0) {
            num /= 4;
        }

        return num == 1;
    }
}

/*
x = 4^n <=> x = 2^2n && (x - 1) % 3 == 0
Proof (Mathematical Induction):
1. n = 1 => 2 ^ (2 * 1) - 1 = 3 correct
2. suppose (4^n - 1) % 3 == 0 correct
3. 4 ^ (n + 1) = 4 * (4 ^ n - 1) + 3 => %3 == 0 => correct
*/
public class Solution {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }
}

/*
find characteristic in bit:
x = 4^n <=> x = 2^2n && x = ...010100
*/
public class Solution {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
    }
}
