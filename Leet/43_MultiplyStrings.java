class Solution {
    public String multiply(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[] res = new int[m + n];

        // save result in res[]
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                // y contains result for cur iteration
                // x contains carry-on value for next iteration
                int x = i + j;
                int y = i + j + 1;
                int sum = (a.charAt(i) - '0') * (b.charAt(j) - '0') + res[y];
                // add carry-on to for higher bit
                res[x] += sum / 10;
                // save cur result
                res[y] = sum % 10;
            }
        }

        StringBuilder str = new StringBuilder();
        int i = 0;
        // skip '0' in front
        // leave last 0 and return as result
        while (i < res.length - 1 && res[i] == 0) {
            i++;
        }
        // convert from res[] to str
        while (i < res.length) {
            str.append(res[i++]);
        }

        return str.toString();
    }
}
