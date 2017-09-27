/*
if
*/
public boolean isOneEditDistance(String s, String t) {
    int m = s.length();
    int n = t.length();
    // when size diff larger than one
    // there is no way to convert with 1 step
    if (Math.abs(m - n) > 1) {
        return false;
    }
    for (int i = 0; i < Math.min(m, n); i++) {
    	if (s.charAt(i) != t.charAt(i)) {
    		if (m == n) {
                // s has the same length as t, there should be only one diff char
                //  hence all remaining should be identical
        		return s.substring(i + 1).equals(t.substring(i + 1));
            }
			else if (m < n) {
                // t is longer than s
                // after deleting one char from t, remaining should be the same
    			return s.substring(i).equals(t.substring(i + 1));
            }
			else {
                // s is longer than t
                // similar as above
    			return t.substring(i).equals(s.substring(i + 1));
            }
    	}
    }
    // All previous chars are the same
    // check if deleting the end char in the longer one works 
    return Math.abs(m - n) == 1;
}
