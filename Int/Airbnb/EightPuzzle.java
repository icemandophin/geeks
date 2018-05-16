/**
 * https://en.wikipedia.org/wiki/15_puzzle
 * 就是wikipeida里的问题换成九宫格，有8个版
 * 这里我们假设空格为0，所以0周围的数字可以与其交换
 *
 * 最好的应该是A*算法，这里用BFS也是可以做的。最好不要DFS，可能会爆栈。
 * 面经里应该只需要判断是否能solve，其实打印出最短路径也是差不多的
 */

import java.util.*;

public class EightPuzzle {
  private static final int[][] DIRS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
  private int[][] matrix;
  private int m;
  private int n;
  private int originX;
  private int originY;
  private String recovered;

  public EightPuzzle(int[][] matrix) {
    this.matrix = matrix;
    this.m = matrix.length;
    this.n = matrix[0].length;
    int[][] recoveredMatrix = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          this.originX = i;
          this.originY = j;
        }

        recoveredMatrix[i][j] = i * n + j;
      }
    }
    this.recovered = getMatrixString(recoveredMatrix);
  }

  // Check if it can be solved
  public boolean canSolve() {
    Queue<int[]> elementQ = new LinkedList<>();
    Queue<String> matrixQ = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    String stringMatrix = getMatrixString(matrix.clone());
    elementQ.offer(new int[] { originX, originY });
    matrixQ.offer(stringMatrix);
    visited.add(stringMatrix);

    while (!elementQ.isEmpty()) {
      int size = elementQ.size();
      for (int i = 0; i < size; i++) {
        int[] curElement = elementQ.poll();
        String curMatrixString = matrixQ.poll();
        int x = curElement[0];
        int y = curElement[1];

        if (curMatrixString.equals(recovered)) {
          return true;
        }

        for (int k = 0; k < DIRS.length; k++) {
          int xx = x + DIRS[k][0];
          int yy = y + DIRS[k][1];

          if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
            continue;
          }

          int[][] newMatrix = recoverMatrixString(curMatrixString);
          int temp = newMatrix[x][y];
          newMatrix[x][y] = newMatrix[xx][yy];
          newMatrix[xx][yy] = temp;
          String newMatrixString = getMatrixString(newMatrix);
          if (visited.contains(newMatrixString)) {
            continue;
          }

          elementQ.offer(new int[] { xx, yy });
          matrixQ.offer(newMatrixString);
          visited.add(newMatrixString);
        }
      }
    }

    return false;
  }

  // Print shortest path
  public List<String> getSolution() {
    String[] pathWord = { "Down", "Right", "Up", "Left" };

    Queue<int[]> elementQ = new LinkedList<>();
    Queue<String> matrixQ = new LinkedList<>();
    Queue<List<String>> pathQ = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    String stringMatrix = getMatrixString(matrix.clone());
    elementQ.offer(new int[] { originX, originY });
    matrixQ.offer(stringMatrix);
    pathQ.offer(new ArrayList<>());
    visited.add(stringMatrix);

    while (!elementQ.isEmpty()) {
      int size = elementQ.size();
      for (int i = 0; i < size; i++) {
        int[] curElement = elementQ.poll();
        String curMatrixString = matrixQ.poll();
        List<String> curPath = pathQ.poll();
        int x = curElement[0];
        int y = curElement[1];

        if (curMatrixString.equals(recovered)) {
          return curPath;
        }

        for (int k = 0; k < DIRS.length; k++) {
          int xx = x + DIRS[k][0];
          int yy = y + DIRS[k][1];

          if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
            continue;
          }

          int[][] newMatrix = recoverMatrixString(curMatrixString);
          int temp = newMatrix[x][y];
          newMatrix[x][y] = newMatrix[xx][yy];
          newMatrix[xx][yy] = temp;
          String newMatrixString = getMatrixString(newMatrix);
          if (visited.contains(newMatrixString)) {
            continue;
          }

          List<String> newPath = new ArrayList<>(curPath);
          newPath.add(pathWord[k]);

          elementQ.offer(new int[] { xx, yy });
          matrixQ.offer(newMatrixString);
          pathQ.offer(newPath);
          visited.add(newMatrixString);
        }
      }
    }

    return new ArrayList<>();
  }

  private String getMatrixString(int[][] matrix) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        sb.append(matrix[i][j]).append(",");
      }
    }
    return sb.toString();
  }

  private int[][] recoverMatrixString(String str) {
    String[] parts = str.split(",");
    int[][] res = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        res[i][j] = Integer.parseInt(parts[i * n + j]);
      }
    }
    return res;
  }
}

//class Main {
//  public static void main(String[] args) {
//    int[][] matrix = {
//            {3, 1, 4},
//            {6, 2, 0},
//            {7, 5, 8}
//    };
//    EightPuzzle ep = new EightPuzzle(matrix);
//    System.out.println(ep.canSolve());
//    System.out.println(ep.getSolution());
//  }
//}