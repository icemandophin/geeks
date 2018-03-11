/*
There are 2 cases that makes palindrome pair:
1. "abc" and "cba" both exist in words[] => they make 2 pairs
2. "abcddd" and "cba", or "aabcd" and "dcb" => part of longer word is palindrome, and reverse of remaining exist in words[]
=> build hashmap of indexing, then parition each word and check the conditions

Notice:
Consider the test case of ["a", ""] => "" should also consider empty when constructing palindrome pairs
=> use <= in for (int j=0; j<=words[i].length(); j++)
=> may be duplicates in the output (consider test case [“abcd”, “dcba”])
=> add condition str2.length()!=0 to avoid duplicates
*/

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) {
            // there is no pair
            return res;
        }
        //build map of word -> index
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            map.put(words[i], i);
        }
        // traverse and find pair for each word
        for (int i = 0; i < words.length; ++i) {
            // "" can make palindrome with "a" => s1 can be full length
            for (int j = 0; j <= words[i].length(); ++j) {
                String s1 = words[i].substring(0, j);
                String s2 = words[i].substring(j);
                // if s1 is palindrome, and reverse of s2 exist in map
                // rev(s2) + words[i] makes palindrome
                if (checkPalindrome(s1)) {
                    String rs2 = new StringBuilder(s2).reverse().toString();
                    // check if reverse is itself, which does not count as pair
                    if (map.containsKey(rs2) && map.get(rs2) != i) {
                        int k = map.get(rs2);
                        List<Integer> pair = new ArrayList<>();
                        pair.add(k);
                        pair.add(i);
                        res.add(pair);
                    }
                }
                // check if s2 is palindrome and reverse of s1 exist for pairing
                if (checkPalindrome(s2)) {
                    String rs1 = new StringBuilder(s1).reverse().toString();
                    // notice that "" + full word scenario is covered above
                    // avoid s2 == "" as duplicated
                    if (map.containsKey(rs1) && map.get(rs1) != i && s2.length() != 0) {
                        int k = map.get(rs1);
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(k);
                        res.add(pair);
                    }
                }
            }
        }

        return res;
    }

    private boolean checkPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;

        while (i <= j) {
            if (str.charAt(j--) != str.charAt(i++)) {
                return false;
            }
        }

        return true;
    }
}
