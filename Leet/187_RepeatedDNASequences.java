/*
brutal approach: Time O(n), Space O(10n)
take size 10 sliding window substring as key, count as value
*/
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        final List<String> result = new ArrayList<>();
        if (s.length() < 10) return result;

        final Map<String, Integer> counter = new HashMap<>();
        // traverse string with sliding window of size 10
        for (int i = 0; i < s.length() - 9; ++i) {
            final String key = s.substring(i, i + 10);
            int value = counter.getOrDefault(key, 0);
            counter.put(key, value + 1);
        }
        // traverse hash map and find count > 1
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
/*
optimize with perfect hashing: Time O(n), Space O(2n)
str only contains 'A', 'T' , 'C' , 'G' => encode with 2-bit int value:
A -> 00
C -> 01
G -> 10
T -> 11
=> any size 10 substring can be encoded to 20-bit value store in Integer space
Need to HashSet: one records if cur int key exist
the other records if existing key has been added to res before (avoid dup)
*/
public class Solution {
    public static List<String> findRepeatedDnaSequences(String str) {
        List<String> res = new ArrayList<>();
        if (str.length() < 10) {
            return res;
        }
        // convert substring to int key, and store in hash set
        Set<Integer> set = new HashSet<>();
        // build another dup hashset to avoid add repeated substring to result
        Set<Integer> dup = new HashSet<>();
        // build ACGT to int mapping with hash map
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        // traverse sliding window of size 10
        for (int i = 0; i < str.length() - 10 + 1; ++i) {
            // get substring in window
            String cur = str.substring(i, i + 10);
            // convert to hash key
            int key = strToInt(cur, map);
            // check if cur substring already exist
            if (set.contains(key)) {
            	if (!dup.contains(key)) {
                    // 1st time cur substring duplicate
                    // add to res
                    res.add(cur);
                    // mark it as added
                    dup.add(key);
            	}
        		// substring appeared more than once
        		// ignore and continue
            } else {
                // mark it as appeared (once)
                set.add(key);
            }
        }
        return res;
    }
    // convert substring to int as hash key
    private static int strToInt(String s, Map<Character, Integer> map) {
        assert s.length() == 10;
        int res = 0;
        for (int i = 0; i < 10; ++i) {
            // shift 2 bit and add new decode
            res <<= 2;
            res += map.get(s.charAt(i));
        }
        return res;
    }
}
/*
further optimization: rolling hash
key &= 0xFFFFF to fit 20-bit hash key window
when sliding window, just remove the oldest 2 bits and add 2 new bits
*/
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicate = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            num <<= 2;
            num += map.get(s.charAt(i));
            num &= 0xFFFFF;

            if (i >= 9 && !set.add(num) && duplicate.add(num)) {
                result.add(s.substring(i - 9, i + 1));
            }
        }

        return result;
    }
}
