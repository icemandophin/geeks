/*
find each "++" pair, convert to "--" and save to res
*/
public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                result.add(s.substring(0, i) + "--" + s.substring(i + 2));
            }
        }

        return result;
    }
}
