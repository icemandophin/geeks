class Solution {
    public int findMinStep(String board, String hand) {
        int[] map = new int[26];
        // map contains all available balls for backtrack
        for (int i = 0; i < hand.length(); ++i) {
            map[hand.charAt(i) - 'A']++;
        }
        // try all possible approaches and return min count
        return dfs(board, map);
    }
    // DFS + backtrack with map
    private int dfs(String a, int[] map) {
        if (a.length() == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        int i = 0;

        for (int j = 1; j <= a.length(); ++j) {
            if (j == a.length() || a.charAt(j) != a.charAt(i)) {
                // get ball and cnt needed to remove a[i : j - 1]
                int need = 3 - (j - i);
                int ch = a.charAt(i) - 'A';

                if (map[ch] >= need) {
                    // clear a[i : j - 1] and check remaining
                    map[ch] -= need;
                    int cnt = dfs(clearBoard(a.substring(0, i) + a.substring(j)), map);

                    if (cnt != -1) {
                        // cur clear can remove all balls => update min count
                        res = Math.min(res, cnt + need);
                    }
                    // backtrack
                    map[ch] += need;
                }

                i = j;
            }
        }

        if (res == Integer.MAX_VALUE) {
            return -1;
        } else {
            return res;
        }
    }
    // remove all balls that 3 or more in a line - recursive
    private String clearBoard(String a) {
        int i = 0;

        for (int j = 1; j <= a.length(); ++j) {
            if (j == a.length() || a.charAt(j) != a.charAt(i)) {
                if (j - i >= 3) {
                    // found session to delete
                    // remove a[i : j - 1] and clear remaining recursively
                    return clearBoard(a.substring(0, i) + a.substring(j));
                } else {
                    // cannot delete => move i forward
                    i = j;
                }
            }
        }

        return a;
    }
}
