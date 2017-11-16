/*
parse and digit-by-digit compare
1.0 equals to 1 => use 0 as default value
*/
class Solution {
    public int compareVersion(String ver1, String ver2) {
        String[] s1 = ver1.split("\\.");
        String[] s2 = ver2.split("\\.");
        int n = Math.max(s1.length, s2.length);
        for (int i = 0; i < n; ++i) {
            Integer d1 = i < s1.length ? Integer.parseInt(s1[i]) : 0;
            Integer d2 = i < s2.length ? Integer.parseInt(s2[i]) : 0;
            //compare cur digit
            int comp = d1.compareTo(d2);
            if (comp != 0) {
                return comp;
            }
        }

        // cover all digits match scenario
        return 0;
    }
}
