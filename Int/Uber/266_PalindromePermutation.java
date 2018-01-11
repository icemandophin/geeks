/*
string has to contain at most 1 odd-count char to be permutable
=> hashset records cur odd-count chars
*/
public class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> rec = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // add/remove per odd/even
            if (rec.contains(ch)) {
                rec.remove(ch);
            } else {
                rec.add(ch);
            }
        }
        // check # of odd-count chars
        return rec.size() <= 1;
    }
