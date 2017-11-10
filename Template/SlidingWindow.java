public class Solution {
    public List<Integer> slidingWindow(String s, String t) {
        // init a collection or int value to save the result
        List<Integer> result = new LinkedList<>();
        if (t.length() > s.length()) {
            return result;
        }
        // create hashmap to save the Characters of the target substring
        // (K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // maintain a counter to check whether match the target string
        // must be the map size, NOT the string size because the char may be duplicate
        int counter = map.size();
        // Two Pointers: begin - left pointer of the window
        // end - right pointer of the window
        int begin = 0, end = 0;
        //the length of the substring which match the target string
        int len = Integer.MAX_VALUE;
        // loop at the begining of the source string
        while(end < s.length()) {
            // get a character
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                // plus or minus one
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    // modify the counter according the requirement
                    // (different condition)
                    counter--;
                }
            }
            end++;
            //increase begin pointer to make it invalid/valid again
            /* counter condition. different question may have different condition */
            while (counter == 0) {
                //***be careful here: choose the char at begin pointer, NOT the end pointer
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)){
                    // plus or minus one
                    map.put(tempc, map.get(tempc) + 1);
                    // modify the counter according the requirement(different condition).
                    if (map.get(tempc) > 0) {
                        counter++;
                    }
                }
                /* save / update(min/max) the result if find a target*/
                // result collections or result int value
                begin++;
            }
        }

        return result;
    }
}
