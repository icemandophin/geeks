/*
start BFS from begin word => 1st time reach end word makes min length
path consistancy: check and add words that exist in set into queue
trim: avoid repeated search on same word => remove it from dict
*/
class Solution {
    public int ladderLength(String begin, String end, List<String> wordList) {
        // record length from begin so far
        int res = 1;
        Set<String> dict = new HashSet<>(wordList);
        // queue holds transformed words that EXIST in dict
        Queue<String> q = new LinkedList<>();
        // insert begin word
        q.offer(begin);

        // level order BFS
        while (!q.isEmpty()) {
            int n = q.size();
            res++;
            // traverse cur layer
            for (int i = 0; i < n; ++i) {
                String cur = q.poll();
                // get all possible words with one edit
                List<String> list = transform(cur);

                for (String word : list) {
                    // trim: only add words that exist in dict to queue
                    if (dict.contains(word)) {
                        // with BFS the first time reach end makes min path
                        if (word.equals(end)) {
                            return res;
                        }
                        // enqueue possible words for next layer BFS
                        q.offer(word);
                        // trim: remove word from dict to avoid repeated search
                        dict.remove(word);
                    }
                }
            }
        }

        return 0;
    }

    // given word, return all transformed words with 1 edit distance
    private List<String> transform(String str) {
        List<String> res = new ArrayList<>();
        // try change in every char
        for (int i = 0; i < str.length(); ++i) {
            // make mutable instance of str
            StringBuilder cur = new StringBuilder(str);
            // try change with a - z
            for (char j = 'a'; j <= 'z'; ++j) {
                if (j != str.charAt(i)) {
                    cur.setCharAt(i, j);
                    String made = cur.toString();
                    res.add(made);
                }
            }
        }

        return res;
    }
}
