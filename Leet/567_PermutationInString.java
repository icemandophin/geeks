/*
permutation share the same freq cnt for char
  direct compare 2 hashmap cause TLE\
                                     use cnt[26] array for O(1) update/compare
         s1, s2 only contains letter/

Time complexity : O(m + 26 * m * (n - m))
Space complexity : O(1)
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int m = s1.length();
        int n = s2.length();

        int[] cnt1 = new int[26];
        for (int i = 0; i < m; ++i) {
            cnt1[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i <= n - m; ++i) {
            // cnt freq for cur s2 substring which has the same length as s1
            int[] cnt2 = new int[26];
            for (int j = i; j < i + m; ++j) {
                cnt2[s2.charAt(j) - 'a']++;
            }
            // check if match
            if (match(cnt1, cnt2)) {
                return true;
            }
        }

        return false;
    }

    private boolean match(int[] a, int[] b) {
        for (int i = 0; i < 26; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}

/*
optimize: sliding window with matching char count
during traverse of s2, update num of matched char for cur substring
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int m = s1.length();
        int n = s2.length();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        // init with top m chars
        for (int i = 0; i < m; ++i) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        // record num of chars whose freq match in s1 and s2
        int match = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] == cnt2[i]) {
                match++;
            }
        }
        // only top/end element change during each iteration
        for (int i = 0; i < n - m; ++i) {
            // get first/last char of cur subarray
            int top = s2.charAt(i) - 'a';
            int end = s2.charAt(i + m) - 'a';
            // check for match
            if (match == 26) {
                return true;
            }

            cnt2[end]++;
            if (cnt2[end] == cnt1[end]) {
                match++;
            } else if (cnt2[end] == cnt1[end] + 1) {
                // indicate cnt2[end] and cnt1[end] used to match
                // but they no longer matched after move => match decrease
                match--;
            }

            cnt2[top]--;
            if (cnt2[top] == cnt1[top]) {
                match++;
            } else if (cnt2[top] == cnt1[top] - 1) {
                // prev matched, not match now due to move
                match--;
            }
        }

        return match == 26;
    }
}
