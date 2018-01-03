class RandomizedCollection {
    private List<Integer> num;
    private Map<Integer, Set<Integer>> map;
    private Random rand;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        num = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        int idx = num.size();
        num.add(val);

        if (map.containsKey(val)) {
            map.get(val).add(idx);
            return false;
        } else {
            Set<Integer> cur = new HashSet<>();
            cur.add(idx);
            map.put(val, cur);
            return true;
        }

    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            Set<Integer> cur = map.get(val);
            // get 1st index in hashset
            int idx = cur.iterator().next();
            cur.remove(idx);
            // get last index of list
            int last = num.size() - 1;
            if (idx != last) {
                int fill = num.get(last);
                // swap value of list[i] with fill/list[n-1]
                // then delete last element in O(1)
                map.get(fill).add(idx);
                map.get(fill).remove(last);
                num.set(idx, fill);
            }
            num.remove(last);

            // remove empty set
            if (cur.isEmpty()) {
                map.remove(val);
            }

            return true;
        } else {
            return false;
        }
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return num.get(rand.nextInt(num.size()));
    }
}
