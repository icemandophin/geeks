class Solution {
    public String longestCommonPrefix(String[] strs) {
        if((strs == null) || (strs.length == 0))
        {
            return "";
        }

        String res = new String();
        int i, j = 0;
        char tmp;

        for(j = 0; j < strs[0].length(); j++)
        {
            tmp = strs[0].charAt(j);
            for(i = 1; i < strs.length; i++)
            {
                if((j >= strs[i].length()) || (tmp != strs[i].charAt(j)))
                {
                    return res;
                }
            }

            res += Character.toString(tmp);
        }

        return res;

    }
}
