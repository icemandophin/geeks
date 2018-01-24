class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        // build and keep min heap of size k
        // compare (1) freq (2) alphabetical
        Queue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                int res = a.getValue() - b.getValue();
                if (res != 0) {
                    // smaller freq comes first
                    return res;
                } else {
                    // "larger" alphabetical comes first
                    return b.getKey().compareTo(a.getKey());
                }
            }
        });
        // build freq map
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // build and keep min heap of size k
        for (Map.Entry<String, Integer> cur: map.entrySet()) {
            heap.offer(cur);
            // keep size
            if (heap.size() > k) {
                heap.poll();
            }
        }
        // heap contains top k freq words in reverse order
        while (!heap.isEmpty()) {
            res.add(heap.poll().getKey());
        }
        Collections.reverse(res);

        return res;
    }
}
