/*
level-order BFS with queue => this ensure 1st match is min path
1. for each piece, generate all permutations with one edit distance
2. for permutation that match for bank, enqueue and check if match end

Notice: each time enqueue a mutation, remove it from hash set
=> avoid repeated BFS from same mutation
*/
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        // record BFS path length so far
        int res = 0;
        char[] genes = { 'A', 'T', 'C', 'G' };
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        // hold mutations that match in bank/set
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        set.remove(start);

        while (!q.isEmpty()) {
            int n = q.size();
            res++;
            // get and match gene mutataion of cur level
            for (int i = 0; i < n; ++i) {
                String cur = q.poll();
                // get all mutations of cur
                List<String> list = getPermutations(cur, genes);

                for (String str : list) {
                    // if match - compare to end and enqueue
                    if (set.contains(str)) {
                        if (str.equals(end)) {
                            // found match
                            return res;
                        } else {
                            // enqueue for further match
                            q.offer(str);
                            // remove str from set to avoid duplicated search
                            set.remove(str);
                        }
                    }
                }
            }
        }

        return -1;
    }
    // generate all permutation for input gene piece
    private List<String> getPermutations(String str, char[] genes) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < str.length(); ++i) {
            StringBuilder cur = new StringBuilder(str);
            for (char ch : genes) {
                if (ch != str.charAt(i)) {
                    cur.setCharAt(i, ch);
                    res.add(cur.toString());
                }
            }
        }

        return res;
    }
}
