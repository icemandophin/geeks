/**
 * 本质上是word search 2
 * 但是呢比如你现在走了一个词apple, 那么a, p, p, l, e这几个char的位置不能继续用了
 * 于是给你一个board, 一个dict让你计算最多能有多少个valid单词出现在这个Board上面
 */

import java.util.*;

class ReturnType {
  boolean hasPrefix;
  boolean hasWord;
  ReturnType(boolean hasPrefix, boolean hasWord) {
    this.hasPrefix = hasPrefix;
    this.hasWord = hasWord;
  }
}

class TrieNode {
  char c;
  boolean isEnd;
  Map<Character, TrieNode> children;
  public TrieNode(char c, boolean isEnd) {
    this.c = c;
    this.isEnd = isEnd;
    this.children = new HashMap<>();
  }
}

class Trie {
  private TrieNode root;
  public Trie() {
    this.root = new TrieNode(' ', false);
  }

  public void insert(String word) {
    TrieNode cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.children.containsKey(c)) {
        cur.children.put(c, new TrieNode(c, false));
      }
      cur = cur.children.get(c);
    }
    cur.isEnd = true;
  }

  public ReturnType search(String word) {
    TrieNode cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.children.containsKey(c)) {
        return new ReturnType(false, false);
      }
      cur = cur.children.get(c);
    }
    return new ReturnType(true, cur.isEnd);
  }
}

public class BoggleGame {
  private static final int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
  public List<String> findMostStr(char[][] board, Set<String> dict) {
    List<List<int[]>> paths = new ArrayList<>();

    Trie trie = new Trie();
    for (String word : dict) {
      trie.insert(word);
    }

    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        boolean[][] visited = new boolean[m][n];
        visited[i][j] = true;
        List<int[]> curPath = new ArrayList<>();
        curPath.add(new int[] { i, j });
        dfs(paths, board, i, j, trie, visited, curPath);
      }
    }

    List<String> res = new ArrayList<>();
    searchWords(res, new ArrayList<>(), paths, 0, new boolean[m][n], board);

    return res;
  }

  private void searchWords(List<String> res, List<String> curWords, List<List<int[]>> paths,
                           int start, boolean[][] visited, char[][] board) {
    if (start == paths.size()) {
      if (curWords.size() > res.size()) {
        res.clear();
        res.addAll(curWords);
      }
      return;
    }

    for (int i = start; i < paths.size(); i++) {
      boolean canUse = true;
      for (int[] coor : paths.get(i)) {
        if (visited[coor[0]][coor[1]]) {
          canUse = false;
          break;
        }
      }

      if (canUse) {
        for (int[] coor : paths.get(i)) {
          visited[coor[0]][coor[1]] = true;
        }
        curWords.add(path2Word(board, paths.get(i)));
        searchWords(res, curWords, paths, i + 1, visited, board);
        curWords.remove(curWords.size() - 1);
        for (int[] coor : paths.get(i)) {
          visited[coor[0]][coor[1]] = false;
        }
      }
    }
  }

  private void dfs(List<List<int[]>> paths, char[][] board, int x, int y, Trie trie,
                   boolean[][] visited, List<int[]> curPath) {
    String curWord = path2Word(board, curPath);
    ReturnType flag = trie.search(curWord);
    if (!flag.hasPrefix) {
      return;
    }
    if (flag.hasWord) {
      paths.add(new ArrayList<>(curPath));
    }

    int m = board.length;
    int n = board[0].length;

    for (int[] dir : dirs) {
      int xx = x + dir[0];
      int yy = y + dir[1];

      if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
        continue;
      }

      visited[xx][yy] = true;
      curPath.add(new int[] { xx, yy });
      dfs(paths, board, xx, yy, trie, visited, curPath);
      curPath.remove(curPath.size() - 1);
      visited[xx][yy] = false;
    }
  }

  private String path2Word(char[][] board, List<int[]> curPath) {
    StringBuilder sb = new StringBuilder();
    for (int[] coor : curPath) {
      sb.append(board[coor[0]][coor[1]]);
    }
    return sb.toString();
  }
}

//class Main {
//  public static void main(String[] args) {
//    BoggleGame bg = new BoggleGame();
//    char[][] board = {
//            { 'o','a','t','h' },
//            { 'e','t','a','e' },
//            { 'i','h','k','r' },
//            { 'i','f','l','v' }
//    };
//    Set<String> dict = new HashSet<>();
//    dict.add("oath");
//    dict.add("pea");
//    dict.add("eat");
//    dict.add("rain");
//    System.out.println(bg.findMostStr(board, dict));
//  }
//}


public class Solution {
  public static void main(String[] args) {
    BoggleGame bg = new BoggleGame();
    char[][] board = {
            { 'o','a','t','h' },
            { 'e','t','a','e' },
            { 'i','h','k','r' },
            { 'i','f','l','v' }
    };
    Set<String> dict = new HashSet<>();
        /*
        dict.add("oath");
        dict.add("pea");
        dict.add("eat");
        dict.add("rain");
        dict.add("eii");
        */
    dict.add("oath");
    dict.add("eat");
    dict.add("thf");
    dict.add("erv");
    dict.add("akl");

    System.out.println(bg.findMostStr(board, dict));
  }
}

class BoggleGame {
  private int[][] mov = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

  public BoggleGame() {}

  public List<String> findMostStr(char[][] b, Set<String> dict) {
    List<String> res = new ArrayList<>();
    Trie trie = new Trie();
    for (String d : dict) {
      trie.add(d);
    }

    int m = b.length;
    int n = b[0].length;
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        boolean[][] visited = new boolean[m][n];
        List<Grid> path = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        dfs(b, trie, trie.root, visited, cur, path, i, j);

        if (cur.size() > res.size()) {
          res.clear();
          res.addAll(cur);
        }
      }
    }

    return res;
  }

  private void dfs(char[][] b, Trie trie, Node root, boolean[][] visited, List<String> cur, List<Grid> path, int i, int j) {
    int m = b.length;
    int n= b[0].length;
    if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
      return;
    }

    char c = b[i][j];
    if (!root.next.containsKey(c)) {
      return;
    }

    path.add(new Grid(i, j));
    root = root.next.get(c);
    if (!root.word.isEmpty()) {
      markGrids(path, visited);
      cur.add(root.word);
      root = trie.root;
    }

    for (int[] dir : mov) {
      int x = i + dir[0];
      int y = j + dir[1];

      dfs(b, trie, root, visited, cur, path, x, y);
    }
  }

  private void markGrids(List<Grid> path, boolean[][] visited) {
    for (Grid g : path) {
      visited[g.x][g.y] = true;
    }

    path.clear();
  }
}

class Node {
  public char c;
  public String word;
  Map<Character, Node> next;
  public Node(char ch) {
    c = ch;
    word = "";
    next = new HashMap<>();
  }
}

class Trie {
  public Node root;

  public Trie() {
    root = new Node('*');
  }

  public void add(String word) {
    Node cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.next.containsKey(c)) {
        cur.next.put(c, new Node(c));
      }
      cur = cur.next.get(c);
    }
    cur.word = word;
  }

  public Response find(String word) {
    Node cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.next.containsKey(c)) {
        return new Response(false, "");
      }
      cur = cur.next.get(c);
    }
    return new Response(true, cur.word);
  }
}

class Response {
  public boolean check;
  public String word;

  public Response(boolean f, String w) {
    check = f;
    word = w;
  }
}

class Grid {
  public int x;
  public int y;

  public Grid(int a, int b) {
    x = a;
    y = b;
  }
}
