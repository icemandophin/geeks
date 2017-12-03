/*
iterative approach with queue:
eg: "12"
In the res/Queue
before: a - b - c
after:      b - c - ad - ae - af
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        // use res as queue before return final results
        LinkedList<String> res = new LinkedList<>();
        // build mapping for 0 - 9
        String[] dict = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        // each time increase i, makes items in queue valid for adding new char
        for (int i = 0; i < digits.length(); ++i) {
            // get new digit
            int digit = Character.getNumericValue(digits.charAt(i));
            // i is the length of prev mapped digits
            // this loop ends when new digits are mapped and enqueue
            while (res.peek().length() == i) {
                // dequeue prev results
                String cur = res.remove();
                for(char ch : dict[digit].toCharArray()) {
                    res.add(cur + ch);
                }
            }
        }
        return res;
    }
}

/*
recursive DFS for each letter of each digit could match
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        List<String> res = new ArrayList<>();
        // build mapping for 0 - 9
        String[] dict = {" ", "",  "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        // for each digit dfs all possible combinations and add to res
        dfs(digits, dict, 0, "", res);

        return res;
    }

    private void dfs(String digits, String[] dict, int idx, String cur, List<String> res) {
        // after match all digits, add cur case to res and return
        if (idx == digits.length()) {
            res.add(cur);
            return;
        }
        // get idx's mapping
        String str = dict[Character.getNumericValue(digits.charAt(idx))];
        // search for every possibility of subarray
        for (int i = 0; i < str.length(); ++i) {
            // match cur digit to a char and add to "translated" string
            String added = cur + str.charAt(i);
            dfs(digits, dict, idx + 1, added, res);
        }
    }
}
