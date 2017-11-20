/*
In order to make Delete() O(1): locate a[i] and swap with a[n-1] fast
no duplicate value => hashmap
*/
class RandomizedSet {
    // store all set values
    private List<Integer> nums;
    // map value nums[i] and its index i for fast locate
    private Map<Integer, Integer> map;
    // generate i in [0, n-1] and return a[i]
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            // add record to both map and array
            map.put(val, nums.size());
            nums.add(val);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int idx = map.get(val);
            map.remove(val);
            int lastIdx = nums.size() - 1;
            // swap value with last element of array
            if (idx != lastIdx) {
                int num = nums.get(lastIdx);
                // also update hash map
                map.put(num, idx);
                nums.set(idx, num);
            }
            // remove last element from array
            nums.remove(lastIdx);
            return true;
        } else {
            return false;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}
