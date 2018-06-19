/*
Level-order BFS with counting # of transforms from input:
generate new state from cur state by moving '0' to a neighbor place
pruning by recording visited states
*/
class Solution {
    public int slidingPuzzle(int[][] a) {
        String target = "123450";
        Set<String> visit = new HashSet<>();
        int[] mov = {1, -1, 3, -3};
        // convert start array to string without [] or ,
        String s = Arrays.deepToString(a).replaceAll("\\[|\\]|,|\\s", "");
        Queue<String> q = new LinkedList<>();
        q.add(s);
        // record number of transforms from s
        int cnt = 0;

        while (!q.isEmpty()) {
            int n = q.size();

            for (int i = 0; i < n; ++i) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return cnt;
                }
                // find index of '0' in cur string
                int x = cur.indexOf('0');
                // check all possible transforms
                for (int j = 0; j < 4; ++j) {
                    // find one neighbor
                    int y = x + mov[j];
                    // skip invalid transform
                    // notice: a[0][2] and a[1][0] cannot swap
                    if (y < 0 || y > 5 || (x == 2 && y == 3) || (x == 3 && y == 2)) {
                        continue;
                    }
                    // swap '0' and get new state
                    char[] ch = cur.toCharArray();
                    swap(ch, x, y);
                    String next = String.valueOf(ch);
                    // only enqueue when state is new
                    if (visit.add(next)) {
                        q.offer(next);
                    }
                }
            }

            // add count and move BFS next layer
            cnt++;
        }

        // no path found
        return -1;
    }

    private void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
