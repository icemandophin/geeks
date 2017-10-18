/*
Caterpillar traverse - HashMap -
use two index i, j to indicate substring boundary
(1) move j forward until there is repeated char X
(2) jump i to NEXT position of X first appear (so that X is unique in substring)
(3) calculate current max length
(4) go to (1)
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
        {
            return 0;
        }

        HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
        int res = 0;
        int i, j;

        for (i=0, j=0; i<s.length(); ++i)
        {
            if (hash.containsKey(s.charAt(i)))
            {
                j = Math.max(j, hash.get(s.charAt(i))+1);
            }
            hash.put(s.charAt(i), i);
            res = Math.max(res, i-j+1);
        }

        return res;
    }
}
