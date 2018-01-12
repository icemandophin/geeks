class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        // build hashmap: word -> count
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // build and keep min heap of size k
        // which contains top k frequent entries
        // notice: key with same val is sorted by alphabetical order => compareTo() and revert before return
        Queue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
        (a, b) -> a.getValue() != b.getValue() ? Integer.compare(a.getValue(), b.getValue()) : b.getKey().compareTo(a.getKey()));

        for (Map.Entry<String, Integer> cur : map.entrySet()) {
            heap.offer(cur);
            // poll min/root entry when full
            if (heap.size() > k) {
                heap.poll();
            }
        }
        // add to res and revert
        while (!heap.isEmpty()) {
            res.add(heap.poll().getKey());
        }
        Collections.reverse(res);

        return res;
    }
}
