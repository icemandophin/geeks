/*
one traverse approach: record appearance of digits in array
each char range from '0' to '9' => map[10]
for each mismatch (s[i], g[i]):
map[s[i]]++ to mark it appear in s one time
map[g[i]]-- to mark it appear in g one time
when map[cur] != 0 => there is digit match but index mismatch => cow++
*/
class Solution {
    public String getHint(String secret, String guess) {
        int bull = 0;
        int cow = 0;
        // record appearance of each digit
        int[] map = new int[10];

        for (int i = 0; i < secret.length(); ++i) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                bull++;
            } else {
                // cnt++ for digit in secret, cnt-- for digit in guess
                if (map[s]++ < 0) {
                    // there exist prev element in guess that match digit but has diff idx
                    cow++;
                }
                if (map[g]-- > 0) {
                    // there exist prev element in secret that match digit but has diff idx
                    cow++;
                }
            }
        }

        return bull + "A" + cow + "B";
    }
}
