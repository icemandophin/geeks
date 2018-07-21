import java.util.*;

/**
 * 每个人都有一个preference的排序，在不违反每个人的preference的情况下得到总体的preference的排序
 *
 * For example:
 * a: 2, 3, 5
 * b: 4, 2, 1
 * c: 4, 1, 5, 6
 * d: 4, 7
 *
 * Return:
 * 4, 2, 7, 3, 1, 5, 6
 *
 * 拓扑排序解决
 * (follow up break tie with person1)
 */
public class PreferenceList {
  public List<Integer> sortPreference(List<List<Integer>> preferences, int tieBreaker) {
    List<Integer> res = new ArrayList<>();
    Map<Integer, Set<Integer>> edges = new HashMap<>();
    Map<Integer, Integer> indegrees = new HashMap<>();

    for (int i = 0; i < preferences.size(); i++) {
      for (int j = 0; j < preferences.get(i).size(); j++) {
        int thisNode = preferences.get(i).get(j);

        if (!edges.containsKey(thisNode)) {
          edges.put(thisNode, new HashSet<>());
          indegrees.put(thisNode, 0);
        }

        if (j > 0) {
          int lastNode = preferences.get(i).get(j - 1);
          edges.get(lastNode).add(thisNode);
          indegrees.put(thisNode, indegrees.get(thisNode) + 1);
        }
      }
    }

    Set<Integer> tieBreakList = new HashSet<>(preferences.get(tieBreaker));

    Queue<Integer> queue = new LinkedList<>();
    for (int node : indegrees.keySet()) {
      List<Integer> tieBreakerPrefs = new ArrayList<>();
      List<Integer> othersPrefs = new ArrayList<>();
      if (indegrees.get(node) == 0) {
        if (tieBreakList.contains(node)) {
          tieBreakerPrefs.add(node);
        } else {
          othersPrefs.add(node);
        }
      }
      for (int pref : tieBreakerPrefs) {
        queue.offer(pref);
      }
      for (int pref : othersPrefs) {
        queue.offer(pref);
      }
    }

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> tieBreakerPrefs = new ArrayList<>();
      List<Integer> othersPrefs = new ArrayList<>();

      for (int i = 0; i < size; i++) {
        int node = queue.poll();
        res.add(node);
        for (int succ : edges.get(node)) {
          indegrees.put(succ, indegrees.get(succ) - 1);
          if (indegrees.get(succ) == 0) {
            if (tieBreakList.contains(succ)) {
              tieBreakerPrefs.add(succ);
            } else {
              othersPrefs.add(succ);
            }
          }
        }
      }

      for (int pref : tieBreakerPrefs) {
        queue.offer(pref);
      }
      for (int pref : othersPrefs) {
        queue.offer(pref);
      }
    }

    return res;
  }
}

//class Main {
//  public static void main(String[] args) {
//    PreferenceList pl = new PreferenceList();
//    List<List<Integer>> preferences = new ArrayList<>();
//    List<Integer> p1 = new ArrayList<>();
//    p1.add(2);
//    p1.add(3);
//    p1.add(5);
//    List<Integer> p2 = new ArrayList<>();
//    p2.add(4);
//    p2.add(2);
//    p2.add(1);
//    List<Integer> p3 = new ArrayList<>();
//    p3.add(4);
//    p3.add(1);
//    p3.add(5);
//    p3.add(6);
//    List<Integer> p4 = new ArrayList<>();
//    p4.add(4);
//    p4.add(7);
//    preferences.add(p1);
//    preferences.add(p2);
//    preferences.add(p3);
//    preferences.add(p4);
//    System.out.println(pl.sortPreference(preferences, 0));
//  }
//}

public class Solution {
  public static void main(String[] args) {
    PreferenceList pl = new PreferenceList();
    List<List<Integer>> preferences = new ArrayList<>();
    List<Integer> p1 = new ArrayList<>();
    p1.add(2);
    p1.add(3);
    p1.add(5);
    List<Integer> p2 = new ArrayList<>();
    p2.add(4);
    p2.add(2);
    p2.add(1);
    List<Integer> p3 = new ArrayList<>();
    p3.add(4);
    p3.add(1);
    p3.add(5);
    p3.add(6);
    List<Integer> p4 = new ArrayList<>();
    p4.add(4);
    p4.add(7);
    preferences.add(p1);
    preferences.add(p2);
    preferences.add(p3);
    preferences.add(p4);
    System.out.println(pl.sortPreference(preferences, 0));
  }
}

class PreferenceList {
  public List<Integer> sortPreference(List<List<Integer>> pref, int tieBreaker) {
    List<Integer> res = new ArrayList<>();
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();

    for (int i = 0; i < pref.size(); ++i) {
      for (int j = 0; j < pref.get(i).size(); ++j) {
        int cur = pref.get(i).get(j);
        if (!map.containsKey(cur)) {
          map.put(cur, new HashSet<Integer>());
        }

        if (j > 0) {
          int prev = pref.get(i).get(j - 1);
          map.get(prev).add(cur);
        }
      }
    }

    for (Integer i : map.keySet()) {
      dfs(map, set, res, i);
    }

    Collections.reverse(res);

    return res;
  }

  private void dfs(Map<Integer, Set<Integer>> map, Set<Integer> set, List<Integer> res, int idx) {
    if (set.contains(idx)) {
      return;
    }

    set.add(idx);

    for (Integer next : map.get(idx)) {
      dfs(map, set, res, next);
    }

    res.add(idx);
  }
}