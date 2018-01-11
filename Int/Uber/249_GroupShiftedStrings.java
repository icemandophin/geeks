/*
aggregate shifted string per base str (start with 'a')
=> use base str as key of map
*/
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> rec = new HashMap<>();

        for (String str : strings) {
            int offset = 0;
            String key = "";
            // convert shift word to base str which always starts with 'a'
            for (int i = 0; i < str.length(); i++) {
                if (i == 0) {
                    offset = str.charAt(0) - 'a';
                }

                char ch = (char) (str.charAt(i) - offset);

                if (ch < 'a') {
                    ch += 26;
                }

                key += ch;
            }

            if (!rec.containsKey(key)) {
                rec.put(key, new ArrayList<String>());
            }

            rec.get(key).add(str);
        }
        // sort each group in lexicographic order
        for (String key : rec.keySet()) {
            List<String> val = rec.get(key);
            Collections.sort(val);
            result.add(val);
        }

        return result;
    }
}
