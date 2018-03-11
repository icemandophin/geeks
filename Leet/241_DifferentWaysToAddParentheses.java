/*
divide and conquer:
for each operator in str => divide str into 2 parts
and recur calculate possible results
then generate final result from subsets
*/
class Solution {
    // optimize: record all possible res for a str
    // directly return res for repeated str
    private Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String str) {
        if (map.containsKey(str)) {
            return map.get(str);
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                // partition str with cur symbol
                // and get all possible res from left / right part
                List<Integer> left = diffWaysToCompute(str.substring(0, i));
                List<Integer> right = diffWaysToCompute(str.substring(i + 1));
                // generate res list for cur operation
                for (int x : left) {
                    for (int y : right) {
                        if (ch == '+') {
                            res.add(x + y);
                        } else if (ch == '-') {
                            res.add(x - y);
                        } else if (ch == '*') {
                            res.add(x * y);
                        }
                    }
                }
            }
        }

        // when str does not contain operator => direct return digits
        if (res.size() == 0) {
            res.add(Integer.valueOf(str));
        }
        // save result of cur str to map for future reference
        map.put(str, res);

        return res;
    }
}
