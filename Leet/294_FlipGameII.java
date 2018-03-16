/*
1st player try all possible flip ways:
if the is one scenario that makes the other player fail to flip => 1st win
else 1st lost

Optimize: use HashSet for memorization search
*/
public class Solution {
    private Map<String, boolean> map = new HashMap<>();
    public boolean canWin(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                String str = s.substring(0, i) + "--" + s.substring(i + 2);

                if (!canWin(str)) {
                    map.put(s, true);
                    map.put(str, false);
                    return true;
                }
            }
        }

        return false;
    }
}
