public boolean isOneEditDistance(String s, String t) {
    // ensure s is longer
    if (s.length() < t.length()) {
        String tmp = s;
        s = t;
        t = tmp;
    }

    int m = s.length();
    int n = t.length();
    int diff = m - n;

    if (diff > 1) {
        return false;
    } else if (diff == 1) {
        // s has one more char than t
        // compare s.substring(i + 1) and j
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i + 1) == t.substring(i);
            }
        }
    } else {
        // find 1st mismatch
        // skip it and compare substring
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                ++cnt;
            }
        }
        return cnt == 1;
    }

    return true;
}
