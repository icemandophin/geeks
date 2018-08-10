/**
 * There are 10 wizards, 0-9.
 * you are given a list that each entry is a list of wizards known by wizard.
 * Define the cost between wizard[i] and wizard[j] as square of different of i and j
 * i.e. Cost(i, j) = (i - j) ^ 2
 *
 * Find the min cost between 0 and 9. Out put the min cost path.
 *
 * Input
 * List<List<Integer>>, 最外层的List的size保证为10， 一次对应编号为0，1,...,9的人认识那些人
 * Output
 * List<Integer> min cost path
 *
 * This is exactly the same problem as MinCostKStops (Flight Tickets) without k stops constrains.
 *
 * We can also use Dijkstra's Algorithm as well
 */

import java.util.*;

public class WizardShortestDistance {
  public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
    int[] parent = new int[wizards.size()];
    Map<Integer, Wizard> map = new HashMap<>();
    for (int i = 0; i < wizards.size(); i++) {
      parent[i] = i;
      Wizard cur = new Wizard(i);
      for (int j = 0; j < wizards.get(i).size(); j++) {
        int cost = (int)Math.pow(i - wizards.get(i).get(j), 2);
        cur.costs.put(wizards.get(i).get(j), cost);
      }
      map.put(i, cur);
    }

    bfs(map, source, target, parent);

    // Output pathww'w'w'w'w'w'w'w'w'w'w'w'w'w'w'w'w'w'w
    while (cur != source) {
      path.add(cur);
      cur = parent[cur];
    }
    path.add(source);
    Collections.reverse(path);

    return path;
  }

  private void bfs(Map<Integer, Wizard> map, int source, int target, int[] parent) {
    Queue<Integer> idQ = new LinkedList<>();
    Queue<Integer> costQ = new LinkedList<>();
    idQ.offer(source);
    costQ.offer(0);

    while (!idQ.isEmpty()) {
      int size = idQ.size();
      for (int i = 0; i < size; i++) {
        int cur = idQ.poll();
        int curCost = costQ.poll();

        Wizard curWizard = map.get(cur);
        curWizard.dist = Math.min(curWizard.dist, curCost);
        if (cur == target) {
          continue;
        }
        for (int next : curWizard.costs.keySet()) {
          int nextCost = curCost + curWizard.costs.get(next);
          if (nextCost < map.get(next).dist) {
            parent[next] = cur;
            idQ.offer(next);
            costQ.offer(nextCost);
          }
        }
      }
    }
  }
//  // For Dijkstra
//  public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
//    List<Integer> path = new ArrayList<>();
//
//    int[] parent = new int[wizards.size()];
//    Map<Integer, Wizard> map = new HashMap<>();
//    for (int i = 0; i < wizards.size(); i++) {
//      parent[i] = i;
//      map.put(i, new Wizard(i));
//    }
//
//    map.get(source).dist = 0;
//    Queue<Wizard> heap = new PriorityQueue<>();
//    heap.offer(map.get(source));
//
//    while (!heap.isEmpty()) {
//      Wizard cur = heap.poll();
//      List<Integer> neighbors = wizards.get(cur.id);
//      for (int neighbor : neighbors) {
//        Wizard next = map.get(neighbor);
//        int weight = (int)Math.pow(cur.id - next.id, 2);
//        if (cur.dist + weight < next.dist) {
//          heap.remove(next);
//          parent[next.id] = cur.id;
//          next.dist = cur.dist + weight;
//          heap.offer(next);
//        }
//      }
//    }
//
//    int index = target;
//    while (index != source) {
//      path.add(index);
//      index = parent[index];
//    }
//    path.add(source);
//    Collections.reverse(path);
//    return path;
//  }
}

class Wizard {
  int id;
  int dist;
  Map<Integer, Integer> costs;
  Wizard(int id) {
    this.id = id;
    this.dist = Integer.MAX_VALUE;
    this.costs = new HashMap<>();
  }
}

//// For Dijkstra
//class Wizard implements Comparable<Wizard> {
//  int id;
//  int dist;
//  Wizard(int id) {
//    this.id = id;
//    this.dist = Integer.MAX_VALUE;
//  }
//
//  @Override
//  public int compareTo(Wizard that) {
//    return this.dist - that.dist;
//  }
//}

//class Main {
//  public static void main(String[] args) {
//    WizardShortestDistance wsd = new WizardShortestDistance();
//    int[][] ids = { {1,5,9},{2,3,9},{4},{},{},{9},{},{},{},{} };
//    List<List<Integer>> wizards = new ArrayList<>();
//    for (int i = 0; i < ids.length; i++) {
//      List<Integer> wizard = new ArrayList<>();
//      for (int j = 0; j < ids[i].length; j++) {
//        wizard.add(ids[i][j]);
//      }
//      wizards.add(wizard);
//    }
//
//    System.out.println(wsd.getShortestPath(wizards, 0, 9));
//  }
//}
