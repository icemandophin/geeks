class Solution {
    public int compress(char[] ch) {
        // count size of compressed string
        int res = 0;
        int i = 0;
        int n = ch.length;
        while (i < n) {
            char cur = ch[i];
            // count # of repeated c
            int cnt = 0;
            while (i < n && ch[i] == cur) {
                cnt++;
                i++;
            }
            // add cur to string
            ch[res++] = cur;
            // if repeat exist, convert cnt to char and add to string
            if (cnt > 1) {
                for (char x : Integer.toString(cnt).toCharArray()) {
                    ch[res++] = x;
                }
            }
        }

        return res;
    }
}
