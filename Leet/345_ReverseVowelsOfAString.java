/*
two pointer approach: find first top/end Vowels, then swap
*/
class Solution {
    public String reverseVowels(String s) {
        char[] ch = s.toCharArray();
        int i = 0;
        int j = ch.length - 1;
        Character[] vowel = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
        Set<Character> set = new HashSet(Arrays.asList(vowel));

        while (i < j) {
            if (!set.contains(ch[i])) {
                i++;
            } else if (!set.contains(ch[j])) {
                j--;
            } else {
                char tmp = ch[i];
                ch[i++] = ch[j];
                ch[j--] = tmp;
            }
        }

        return new String(ch);
    }
}
