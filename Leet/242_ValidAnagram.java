/*
sort 2 strings and check equal
*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        Arrays.sort(str1);
        Arrays.sort(str2);

        return Arrays.equals(str1, str2);
    }
}

/*
count char freq and match - better for large input
*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] table = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            if (--table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
