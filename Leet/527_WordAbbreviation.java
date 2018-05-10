/*
1. try to abbreviate words with min prefix
2. if same abbreviation exist => abbreviate with longer prefix
3. loop until every abbreviation is unique
*/
public class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        int size = dict.size();
        // record prefix length of each abbreviation so far
        int[] prefix = new int[size];
        // record abbreviation result so far
        String[] result =new String[size];

        for (int i = 0; i < size; i++) {
            prefix[i] = 1;
            // start with prefix length i
            result[i] = getAbbr(dict.get(i), 1);
        }

        for (int i = 0; i < size; i++) {
            while (true) {
                // record same index of words that hava same abbreviation
                Set<Integer> set = new HashSet<>();
                // detect duplicate abbreviation result
                for (int j = i + 1; j < size; j++) {
                    if (result[j].equals(result[i])) {
                        set.add(j);
                    }
                }

                if (set.isEmpty()) {
                    break;
                }

                set.add(i);
                // generate abbreviation with longer prefix
                // until duplicate can be removed
                for (int idx : set) {
                    result[idx] = getAbbr(dict.get(idx), ++prefix[idx]);
                }
            }
        }

        return Arrays.asList(result);
    }
    // make abbreviation of str with prefix length k
    private String getAbbr(String s, int k) {
        int len = s.length();

        if (k >= len - 2) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, k));
        sb.append(len - 1 - k);
        sb.append(s.charAt(len - 1));

        return sb.toString();
    }
}
