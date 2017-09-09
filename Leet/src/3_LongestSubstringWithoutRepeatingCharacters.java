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
