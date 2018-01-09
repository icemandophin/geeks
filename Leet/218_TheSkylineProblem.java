/*
max heap approach: O(NlogN) time, O(N) space
for each building, mark start edge with -h and end edge with h
need to maintain max height during runtime => build max heap:
for start edge => insert h
for end edge => poll h
heap root is cur max height that should be added to res
remove duplicate in res => keep and compare prev max 
*/
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> hei = new ArrayList<>();
        // build list of build's start/end - height pairs
        for (int[] b : buildings) {
            // mark height of start negtive as distinguish
            hei.add(new int[]{b[0], -b[2]});
            hei.add(new int[]{b[1], b[2]});
        }
        // sort node - height pairs on x axis
        Collections.sort(hei, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    // compare y axis when x axis equal
                    return a[1] - b[1];
                }
            }
        });
        // build max heap
        // implement by PQ and sort with height descending (y2 - y1)
        Queue<Integer> pq = new PriorityQueue<>((y1, y2) -> Integer.compare(y2, y1));
        // record prev max height
        int prev = 0;
        // insert 0 as min height for building
        pq.offer(0);
        // traverse height pairs
        for (int[] h: hei) {
            if (h[1] < 0) {
                // cur node is building start
                // offer to max heap of height
                pq.offer(-h[1]);
            } else {
                // cur node is building end
                // remove associated height from heap
                pq.remove(h[1]);
            }
            // height for cur node should be root of heap
            int cur = pq.peek();
            // check if height has changed since last node
            // if height is the same, should not add to res
            if (prev != cur) {
                // node height changes => need to add to res
                res.add(new int[]{h[0], cur});
                prev = cur;
            }
        }

        return res;
    }
}
