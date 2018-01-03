class RandomizedSet {
    private List<Integer> nums;
    private Map<Integer, Integer> map;
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

            if (idx != lastIdx) {
                int num = nums.get(lastIdx);
                map.put(num, idx);
                nums.set(idx, num);
            }

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
