/*
length of 10^6 in binary format is 20 => count of 1s < 20
=> only need to enumerate prime numbers that < 20 for prime check
*/
class Solution {
    public int countPrimeSetBits(int l, int r) {
        Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
        int cnt = 0;
        for (int i = l; i <= r; i++) {
            int bits = 0;
            for (int n = i; n > 0; n >>= 1)
                bits += n & 1;
            cnt += primes.contains(bits) ? 1 : 0;
        }
        return cnt;
    }
}
