/*
only ascending or descending
=> rank ascending then adjust to descending for 'D'
min permutation => find start/end and reverse order in place  
*/
public class Solution {
    public int[] findPermutation(String s) {
        int n = s.length();
        int[] rec = new int[n + 1];
        // build ascending array 1 - n
        for (int i = 0; i <= n; i++) {
            rec[i] = i + 1;
        }
        // record start and end idx of continuous 'D'
        // reverse to make smallest permutation
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'D') {
                // find end idx as i
                int start = i++;
                while (i < n && s.charAt(i) == 'D') {
                    i++;
                }

                reverse(rec, start, i);
            }
        }

        return rec;
    }

    private void reverse(int[] rec, int low, int high) {
        while (low < high) {
            int temp = rec[low];
            rec[low++] = rec[high];
            rec[high--] = temp;
        }
    }
}
