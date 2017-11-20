/*
brutal search: O(N * M)
*/
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG
{
    static int countNegative(int M[][], int n,
                                      int m)
    {
        int count = 0;

        // Follow the path shown using
        // arrows above
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if( M[i][j] < 0 )
                    count += 1;

                // no more negative numbers
                // in this row
                else
                    break;
            }
        }
        return count;
    }

    // Driver program to test above functions
    public static void main (String[] args)
    {
    int M[][] = { {-3, -2, -1, 1},
                  {-2, 2, 3, 4},
                  {4, 5, 7, 8} };

    System.out.println(countNegative(M, 3, 4));
    }
}

/*
optimize: O(N + M)
find largest
*/
class GFG
{
    static int countNegative(int M[][], int n, int m)
    {
        // initialize result
        int count = 0;

        // Start with top right corner
        int i = 0;
        int j = m - 1;

        // Follow the path shown using
        // arrows above
        while( j >= 0 && i < n )
        {
            if( M[i][j] < 0 )
            {
                // j is the index of the
                // last negative number
                // in this row. So there
                // must be ( j+1 )
                count += j + 1;

                // negative numbers in
                // this row.
                i += 1;
            }

            // move to the left and see
            // if we can find a negative
            // number there
            else
            j -= 1;
        }
        return count;
    }
}
