/*
1. consider strings are both empty or identical
2. for large string, traverse from 0 to (include) m-n
*/

class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        int i, j;
        if((m < n) || (m < 0))
        {
            return -1;
        }

        if(haystack.equals(needle))
        {
            return 0;
        }

        for(i = 0; i <= m - n; i++)
        {
            for(j = 0; j < n; j++)
            {
                if(haystack.charAt(i+j) != needle.charAt(j))
                {
                    break;
                }
            }
            if(j == n)
            {
                return i;
            }
        }
        return -1;
    }
}
