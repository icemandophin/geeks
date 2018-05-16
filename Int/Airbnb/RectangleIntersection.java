/**
 * 题意不明，暂时先这么做了
 * Rectangle is defined as a 2D array [[a, b], [c, d]]
 * where (a, b) is the left-bottom corner, (c, d) is the top-right corner
 * Return how many intersection areas
 *
 * For example
 * R1 - R2 - R3 intersect with each other, then return 1
 */

import java.util.HashSet;
import java.util.Set;

public class RectangleIntersection {
  public int countIntersection(int[][][] rectangles) {
    int[] parents = new int[rectangles.length];
    for (int i = 0; i < parents.length; i++) {
      parents[i] = i;
    }

    for (int i = 0; i < rectangles.length; i++) {
      for (int j = i + 1; j < rectangles.length; j++) {
        if (intersect(rectangles[i], rectangles[j])) {
          int root1 = find(i, parents);
          int root2 = find(j, parents);

          if (root1 != root2) {
            parents[root1] = root2;
          }
        }
      }
    }

    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < parents.length; i++) {
      set.add(find(i, parents));
    }

    return set.size();
  }

  private int find(int i, int[] parents) {
    int temp = i;
    while (parents[temp] != temp) {
      temp = parents[temp];
    }
    return temp;
  }

  private boolean intersect(int[][] r1, int[][] r2) {
    return r1[0][0] < r2[0][0] && r2[0][0] < r1[1][0] && r1[0][1] < r2[0][1] && r2[0][1] < r1[1][1] ||
            r1[0][0] < r2[1][0] && r2[1][0] < r1[1][0] && r1[0][1] < r2[1][1] && r2[1][1] < r1[1][1];
  }
}

//class Main {
//  public static void main(String[] args) {
//    RectangleIntersection ri = new RectangleIntersection();
//    int[][][] rectangles = {
//            { { -3, -2 }, { 2, 1 } },
//            { { 10, 8 }, { 15, 10 } },
//            { { 1, 0 }, { 7, 4 } },
//            { { 12, 9 }, { 16, 12 } },
//            { { -2, -1 }, { 5, 3 } }
//    };
//    System.out.println(ri.countIntersection(rectangles));
//  }
//}
