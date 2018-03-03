class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        // build trie from word dict
        List<String> res = new ArrayList<>();
        Trie tree = new Trie();
        tree.insert(words);

        int m = board.length;
        int n = board[0].length;
        boolean[][] visit = new boolean[m][n];
        int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // search trie in board instead of search single word
                dfs(board, i, j, tree.root, move, visit, res);
            }
        }

        return res;
    }
    // search for match in trie with b[i][j] as start of word
    private void dfs(char[][] board, int i, int j, Node root, int[][] move, boolean[][] visit, List<String> res) {
        int m = board.length;
        int n = board[0].length;
        // check cur position b[i][j] is valid for match
        if (i < 0 || i >= m || j < 0 || j >= n || visit[i][j]) {
            return;
        }
        // set visit of b[i][j] before dfs
        visit[i][j] = true;
        // try to match trie from b[i][j]
        if (root.next[board[i][j] - 'a'] != null) {
            Node ch = root.next[board[i][j] - 'a'];
            // add found word to res
            if (ch.word != "" && !res.contains(ch.word)) {
                res.add(ch.word);
            }
            // dfs neighbors to match next layer
            for (int k = 0; k < 4; ++k) {
                int x = i + move[k][0];
                int y = j + move[k][1];

                dfs(board, x, y, ch, move, visit, res);
            }
        }
        // backtrack b[i][j]
        visit[i][j] = false;
    }
}

class Node {
    // no need to record node val
    // next[i] exist => char('a' + i) follows cur node
    public Node[] next;
    // store word in last node for fast add
    public String word;

    public Node() {
        next = new Node[26];
        word = "";
    }
}

class Trie{
    public Node root;

    public Trie() {
        root = new Node();
    }
    // import dict to build trie
    public void insert(String[] words) {
        for (String word : words) {
            Node cur = root;

            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                if (cur.next[ch - 'a'] == null) {
                    cur.next[ch - 'a'] = new Node();
                }
                cur = cur.next[ch - 'a'];
            }

            cur.word = word;
        }
    }
}
