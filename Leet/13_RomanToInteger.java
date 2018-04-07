/*
1. build roman letters mapping
2. add when a[i]>a[i+1], otherwise minus
3. convert string to array in Java
*/

public class Solution {
    public int romanToInt(String s) {
        int res = 0;
        int temp = 0;
        int i = 0;

        HashMap<Character, Integer> hash = new HashMap<Character, Integer>();

        hash.put('I', 1);
        hash.put('V', 5);
        hash.put('X', 10);
        hash.put('L', 50);
        hash.put('C', 100);
        hash.put('D', 500);
        hash.put('M', 1000);

        char sa[] = s.toCharArray();

        for(i = 0; i < s.length(); ++i)
        {
            temp = hash.get(sa[i]);
            if((i == s.length()-1) || (temp >= hash.get(sa[i+1])))
            {
                res += temp;
            }
            else
            {
                res -= temp;
            }
        }
        return res;
    }
}
