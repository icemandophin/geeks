/*
preprocessing: O(M*N) time, O(M+N) space
Manhattan Distance => distance on x and y can be calculated separately
for 1D array, any x between A and B can make min distance of [D  A  B  C]
and the min distance is |A - B| + |C - D|
=> find "gravity center"/median point for row and col,
then calculate x and y distance between median point and each house
*/
public int minTotalDistance(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    ArrayList<Integer> cols = new ArrayList<>();
    ArrayList<Integer> rows = new ArrayList<>();
    // get list of x/y value for each house
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
           if (grid[i][j] == 1) {
               // rows[]/cols[] contains all x/y values of houses
               // row handled by outer loop => value in rows are sorted
               cols.add(j);
               rows.add(i);
           }
        }
    }

    int sum = 0;
    // rows natually sorted => only sort cols
    Collections.sort(cols);
    // get median value for rows and cols
    int x_mid = rows.get(rows.size() / 2);
    int y_mid = cols.get(cols.size() / 2);
    // distance between mid and each house is the min distance overall
    for (Integer i: rows) {
        sum += Math.abs(i - x_mid);
    }
    for (Integer i: cols) {
        sum += Math.abs(i - y_mid);
    }

    return sum;
}

/*
bucket approach:
1. count number of 1s for each x / y value
*/
public class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        int[] x = new int[row];
        int[] y = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    x[i]++;
                    y[j]++;
                }
            }
        }

        return getDist(x) + getDist(y);
    }

    private int getDist(int[] rec) {
        int dist = 0;
        int i = 0;
        int j = rec.length - 1;

        while (i < j) {
            if (rec[i] == 0) {
                i++;
            } else if (rec[j] == 0) {
                j--;
            } else {
                // when there are m, n people in col i, j
                // can remove k = Min{m, n} people
                // their travel distance is 2 * k * (j - i) / 2 = k * (j - i)
                int min = Math.min(rec[i], rec[j]);
                // let k people from i, k people from j move towards some spot
                // in the middle if i, j => total distance is k * (j - i)
                dist += (j - i) * min;
                rec[i] -= min;
                rec[j] -= min;
            }
        }

        return dist;
    }
}
