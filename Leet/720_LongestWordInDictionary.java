/*
Array Sort + HashSet:
add sorter words to set first, check prefix exist to ensure build chain
*/
class Solution {
    public String longestWord(String[] words) {
        // pre-process string array alphabetically so that shorter one always before longer
        Arrays.sort(words);
        Set<String> dict = new HashSet<>();
        String res = "";
        for (String word : words) {
            // single char word is the starter
            // check if prefix (one char shorter) of cur word exist in dict
            if (word.length() == 1 || dict.contains(word.substring(0, word.length() - 1))) {
                // add cur word to dict
                // prefix does not exist => broke the chain => no need to add
                dict.add(word);
                // compare max length and update res
                if (word.length() > res.length()) {
                    res = word;
                }
            }
        }

        return res;
    }
}
