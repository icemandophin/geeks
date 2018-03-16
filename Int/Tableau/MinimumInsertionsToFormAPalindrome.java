/*
dp approach:
dp[i][j] indicates min insertion to make s[i : j] palindrome
if s[i] == s[j] => dp[i][j] = dp[i + 1][j - 1]
else dp[i][j] = Min{dp[i + 1][j] + 1, dp[i][j - 1] + 1}
*/
public class Solution {
    public int findMinInsertionsDP(char str[], int n)
    {
        // Create a table of size n*n. table[i][j]
        // will store minumum number of insertions
        // needed to convert str[i..j] to a palindrome.
        int table[][] = new int[n][n];
        int l, h, gap;

        // Fill the table
        for (gap = 1; gap < n; ++gap)
        for (l = 0, h = gap; h < n; ++l, ++h)
            table[l][h] = (str[l] == str[h])?
                           table[l+1][h-1] :
                          (Integer.min(table[l][h-1],
                                 table[l+1][h]) + 1);

        // Return minimum number of insertions
        // for str[0..n-1]
        return table[0][n-1];
    }
}
