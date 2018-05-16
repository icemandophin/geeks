/**
 * Given a directed graph, find the minimal number of vertices that can traverse all the vertices from them.
 * For example
 * 2->3->1->2->0, 4->5
 * Then we need [1, 4] (or [2, 4], [3, 4]) to traverse all the vertices.
 * Only one solution is needed if there are more than one possibilities.
 */

import java.util.*;

public class MinVerticesTraverseGraph {
  public List<Integer> getMin(int[][] edges, int n) {
    Set<Integer> res = new HashSet<>();

    Map<Integer, Set<Integer>> nodes = new HashMap<>();
    for (int i = 0; i < n; i++) {
      nodes.put(i, new HashSet<>());
    }
    for (int[] edge : edges) {
      nodes.get(edge[0]).add(edge[1]);
    }

    Set<Integer> visited = new HashSet<>();
    for (int i = 0; i < n; i++) {
      if (visited.contains(i)) {
        continue;
      }

      res.add(i);
      visited.add(i);
      Set<Integer> thisTimeVisited = new HashSet<>();
      dfs(res, nodes, i, i, visited, thisTimeVisited);
    }

    return new ArrayList<>(res);
  }

  private void dfs(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start,
                   Set<Integer> visited, Set<Integer> thisTimeVisited) {
    for (int next : nodes.get(cur)) {
      if (res.contains(next) && next != start) {
        res.remove(next);
      }
      if (!thisTimeVisited.contains(next)) {
        thisTimeVisited.add(next);
        visited.add(next);
        dfs(res, nodes, next, start, visited, thisTimeVisited);
      }
    }
  }


}

//public class Main {
//  public static void main(String[] args) {
//    MinVerticesTraverseGraph mvtg = new MinVerticesTraverseGraph();
////    1->2->3->1, 2->0->0
//    int[][] edges1 = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
//    System.out.println(mvtg.getMin(edges1, 4));
////      0  1  2  3  4  5  6  7  8  9
////    0[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
////    1[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
////    2[0, 0, 0 ,0, 0, 0, 0, 0, 0, 1]
////    3[0, 0, 0, 1, 0, 1, 0, 1, 0, 0]
////    4[0, 0, 0, 0, 0, 0 ,0, 0, 1, 0]
////    5[0, 0, 0, 0, 0, 0, 0, 0, 1, 0]
////    6[0, 0, 0, 0, 0, 0, 1, 0, 0 ,0]
////    7[0, 0, 0, 0, 1, 0, 0, 0, 0, 0]
////    8[0, 0, 0, 0, 0, 0, 0, 1, 0, 0]
////    9[0, 0, 0, 1, 0, 0, 1, 0, 0, 0]
//    int[][] edges2 = {{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}};
//    System.out.println(mvtg.getMin(edges2, 10));
////    0->1->0, 2->3->2->1
//    int[][] edges3 = {{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
//    System.out.println(mvtg.getMin(edges3, 4));
//  }
//}