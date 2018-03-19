/*
Optimized: HashMap + bucket sort
handle extra larget sorted stream
*/
class Solution {
    public List<Integer> topKFrequent(int[] a, int k) {
        int n = a.length;
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        // bucket per frequency
        Set<Integer>[] buck = new Set[n + 1];
        for (int i = 0; i <= n; ++i) {
            buck[i] = new HashSet<>();
        }

        for (int i : a) {
            int freq = map.getOrDefault(i, 0) + 1;
            map.put(i, freq);
            // remove i from prev bucket
            if (freq != 1) {
                buck[freq - 1].remove(i);
            }
            // add i to new buck
            buck[freq].add(i);
        }
        // start from max freq buck
        // take first k elements
        for (int j = n; j >= 0; --j) {
            for (Integer i : buck[j]) {
                res.add(i);
                --k;

                if (k == 0) {
                    return res;
                }
            }
        }

        return res;
    }
}

/*
initial approach: similiar to #692
HashMap + MinHeap
*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        int len = nums.length;
    	Map<Integer, Integer> map = new HashMap<>();
    	Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        List<Integer> res = new ArrayList<>();

    	for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
    	}

    	for (int key : map.keySet()) {
            int val = map.get(key);
            queue.offer(new int[] { key, val });

    	    if (queue.size() > k) {
                queue.poll();
            }
    	}

    	while (!queue.isEmpty()) {
            res.add(queue.poll()[0]);
        }

    	return res;
    }
}
