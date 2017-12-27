/*
Implement below methods:
create(int n, int k) // constructor
addMachine(int machine_id) // add a new machine, return a list of shard ids.
getMachineIdByHashCode(int hashcode) // return machine id
*/

/*
micro-sharding:
partition circle segment into N points (N us extreme large)
every hashing of user/data/request is represented as point in segment
when adding new machine, add k points into segment
every user/data/request is is processed by 1st point/machine clockwise
N and K can be 2^64 and 1000
*/

public class Solution {

    public int n, k;
    public Set<Integer> ids = null;
    public Map<Integer, List<Integer>> machines = null;

    // @param n a positive integer
    // @param k a positive integer
    // @return a Solution object
    public static Solution create(int n, int k) {
        // Write your code here
        Solution solution = new Solution();
        solution.n = n;
        solution.k = k;
        solution.ids = new TreeSet<Integer>();
        solution.machines = new HashMap<Integer, List<Integer>>();
        return solution;
    }

    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        // Write your code here
        Random ra =new Random();
        List<Integer> random_nums = new ArrayList<Integer>();
        for (int i = 0; i < k; ++i) {
            int index = ra.nextInt(n);
            while (ids.contains(index))
                index = ra.nextInt(n);
            ids.add(index);
            random_nums.add(index);
        }

        Collections.sort(random_nums);
        machines.put(machine_id, random_nums);
        return random_nums;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {
        // Write your code here
        int distance = n + 1;
        int machine_id = 0;
        for (Map.Entry<Integer, List<Integer>> entry : machines.entrySet()) {
            int key = entry.getKey();
            List<Integer> random_nums = entry.getValue();
            for (Integer num : random_nums) {
                int d = num - hashcode;
                if (d < 0)
                    d += n;
                if (d < distance) {
                    distance = d;
                    machine_id = key;
                }
            }
        }
        return machine_id;
    }
}
