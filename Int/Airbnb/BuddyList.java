/**
 * 我有一个感兴趣的城市wishlist，我的朋友们每个人也有感兴趣的城市wishlist,
 * 如果朋友和我感兴趣的城市占总共他总城市个数的至少一半，那么他就是你的buddy，相同城市越多，similarity越高
 *
 * 1. 按照similarity从高到低把你的buddy排序，输出名字及相同的城市
 * 2. 推荐k个城市，这些城市是不在你的wishlist的城市，从similarity高到低的buddy里面依次找
 *
 * Example
 * Your wishlist: a,b,c,d
 * Buddy1 wishlist: a,b,e,f  (有两个和你的一样，所以是你的buddy)
 * Buddy2 wishlist: a,c,d,g  (有三个和你的一样，也是你的buddy)
 *
 * - k = 10，buddy1和buddy2的wishlist中不在你的wishlist中的城市都可以加入推荐中，因为buddy2的重合度更高，
 *   所以先输出buddy2中的，所以推荐为 g,e,f
 * - k = 2，推荐是g,e 或 g,f
 */

import java.util.*;

public class BuddyList {
  private List<Buddy> buddies;
  private Set<String> myWishList;

  public BuddyList(Set<String> myWishList, Map<String, Set<String>> friendsWishList) {
    this.buddies = new ArrayList<>();
    this.myWishList = myWishList;
    for (String name : friendsWishList.keySet()) {
      Set<String> wishList = friendsWishList.get(name);
      Set<String> intersection = new HashSet<>(wishList);
      // get set of common cities
      intersection.retainAll(myWishList);
      int similarity = intersection.size();
      if (similarity >= wishList.size() / 2) {
        buddies.add(new Buddy(name, similarity, wishList));
      }
    }
  }

  public List<Buddy> getSortedBuddies() {
    Collections.sort(buddies);
    List<Buddy> res = new ArrayList<>(buddies);

    for (Buddy b : res) {
      System.out.print(b.name + " : ");
      for (String city : b.wishList) {
        System.out.print(city + " ");
      }
      System.out.println();
    }

    return res;
  }

  public List<String> recommendCities(int k) {
    List<String> res = new ArrayList<>();
    List<Buddy> buddies = getSortedBuddies();

    int i = 0;
    while (k > 0 && i < buddies.size()) {
      Set<String> diff = new HashSet<>(buddies.get(i).wishList);
      diff.removeAll(myWishList);
      if (diff.size() <= k) {
        res.addAll(diff);
        k -= diff.size();
        i++;
      } else {
        Iterator<String> it = diff.iterator();
        while (k > 0) {
          res.add(it.next());
          k--;
        }
      }
    }

    return res;
  }
}

class Buddy implements Comparable<Buddy> {
  String name;
  int similarity;
  Set<String> wishList;
  Buddy(String name, int similarity, Set<String> wishList) {
    this.name = name;
    this.similarity = similarity;
    this.wishList = wishList;
  }
  @Override
  public int compareTo(Buddy that) {
    return that.similarity - this.similarity;
  }
}

//class Main {
//  public static void main(String[] args) {
//    Set<String> myWishList = new HashSet<>(Arrays.asList(new String[] { "a", "b", "c", "d" }));
//    Set<String> wishList1 = new HashSet<>(Arrays.asList(new String[] { "a", "b", "e", "f" }));
//    Set<String> wishList2 = new HashSet<>(Arrays.asList(new String[] { "a", "c", "d", "g" }));
//    Set<String> wishList3 = new HashSet<>(Arrays.asList(new String[] { "c", "f", "e", "g" }));
//    Map<String, Set<String>> friendWishLists = new HashMap<>();
//    friendWishLists.put("Buddy1", wishList1);
//    friendWishLists.put("Buddy2", wishList2);
//    friendWishLists.put("Buddy3", wishList3);
//    BuddyList bl = new BuddyList(myWishList, friendWishLists);
//    System.out.println(bl.recommendCities(10));
//    System.out.println(bl.recommendCities(2));
//  }
//}

public class Solution {
  public static void main(String[] args) {
    Set<String> myWishList = new HashSet<>(Arrays.asList(new String[] { "a", "b", "c", "d" }));
    Set<String> wishList1 = new HashSet<>(Arrays.asList(new String[] { "a", "b", "e", "f" }));
    Set<String> wishList2 = new HashSet<>(Arrays.asList(new String[] { "a", "c", "d", "g" }));
    Set<String> wishList3 = new HashSet<>(Arrays.asList(new String[] { "c", "f", "e", "g" }));
    Map<String, Set<String>> friendWishLists = new HashMap<>();
    friendWishLists.put("Buddy1", wishList1);
    friendWishLists.put("Buddy2", wishList2);
    friendWishLists.put("Buddy3", wishList3);
    BuddyList bl = new BuddyList(myWishList, friendWishLists);
    System.out.println(bl.recommendCities(10));
    System.out.println(bl.recommendCities(2));
  }
}

class BuddyList {
  private List<Buddy> friends;
  private Set<String> myList;

  public BuddyList(Set<String> myList, Map<String, Set<String>> friendList) {
    this.myList = myList;
    friends = new ArrayList<Buddy>();

    for (Map.Entry<String, Set<String>> entry : friendList.entrySet()) {
      String name = entry.getKey();
      Set<String> set = entry.getValue();
      Set<String> both = new HashSet<>(set);
      both.retainAll(myList);
      int val = both.size();
      if (val >= set.size() / 2) {
        friends.add(new Buddy(name, val, set));
      }
    }
  }

  public List<Buddy> getFriends() {
    Collections.sort(friends);
    List<Buddy> res = new ArrayList<>(friends);

    for (Buddy b : res) {
      System.out.print(b.name + " : ");
      for (String city : b.list) {
        System.out.print(city + " ");
      }
      System.out.println();
    }

    return res;
  }

  public List<String> recommendCities(int k) {
    List<String> res = new ArrayList<>();
    Set<String> set = new HashSet<>();
    List<Buddy> friends = getFriends();
    int i = 0;

    while (i < friends.size() && k > 0) {
      Set<String> cur = new HashSet<>(friends.get(i++).list);
      cur.removeAll(myList);
      Iterator<String> it = cur.iterator();
      while (k > 0 && it.hasNext()) {
        String diff = it.next();
        if (!set.contains(diff)) {
          set.add(diff);
          k--;
        }
      }
    }

    res.addAll(set);

    return res;
  }
}

class Buddy implements Comparable<Buddy> {
  String name;
  int val;
  Set<String> list;

  public Buddy(String name, int val, Set<String> wishList) {
    this.name = name;
    this.val = val;
    list = wishList;
  }

  @Override
  public int compareTo(Buddy b) {
    return Integer.compare(b.val, this.val);
  }

}
