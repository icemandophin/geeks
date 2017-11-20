/*
allow duplicate from # 380 => each value maps to set of indexes
Delete() is always the complicated one
*/
public class RandomizedCollection {
    private List<Integer> nums;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        int idx = nums.size();
        // allow dup - directly add to array
        nums.add(val);

        if (map.containsKey(val)) {
            // add to existing set
            map.get(val).add(idx);
            return false;
        } else {
            // add as 1st index
            Set<Integer> set = new HashSet<>();
            set.add(idx);
            map.put(val, set);
            return true;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            Set<Integer> set = map.get(val);
            int idx = set.iterator().next();
            set.remove(idx);
            int lastIdx = nums.size() - 1;
            // swap with last one
            if (idx != lastIdx) {
                int num = nums.get(lastIdx);
                map.get(num).add(idx);
                map.get(num).remove(lastIdx);
                nums.set(idx, num);
            }
            // remove empty set
            if (set.isEmpty()) {
                map.remove(val);
            }
            // remove deleted from array
            nums.remove(lastIdx);
            return true;
        } else {
            return false;
        }
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        // same as previous problem
        return nums.get(random.nextInt(nums.size()));
    }
}
