/*
Iterative: calculate cur from prev
prev[i] == say => cnt++
prev[i] != say => cnt and say to cur, then update cnt and say
*/

class Solution {
    public String countAndSay(int n) {
        StringBuilder cur = new StringBuilder("1");
        StringBuilder prev;
        int cnt, len, i, j;
        char say;

        for(i = 1; i < n; ++i)
        {
            prev = cur;
            cur = new StringBuilder();
            cnt = 1;
            say = prev.charAt(0);
            len = prev.length();

            for(j = 1; j < len; ++j)
            {
                if(prev.charAt(j) != say)
                {
                    cur.append(cnt).append(say);
                    cnt = 1;
                    say = prev.charAt(j);
                }
                else
                {
                    ++cnt;
                }
            }
            cur.append(cnt).append(say);
        }
        return cur.toString();
    }
}
