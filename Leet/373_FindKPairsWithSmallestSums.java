/*
minimize insertion into min heap before getting k smallest pairs:
(0, 0) always min => insert (1, 0) and (0, 1)
if (1, 0) smaller => add to res and
*/
class Solution {
    public List<int[]> kSmallestPairs(int[] a, int[] b, int k) {
        int m = a.length;
        int n = b.length;
        // contains {x, y}
        List<int[]> res = new ArrayList<>();
        if (m == 0 || n == 0) {
            return res;
        }
        // min heap item format: {a[i] + b[j], i, j}
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        heap.offer(new int[] {a[0] + b[0], 0, 0});
        // add current root/min pair to res
        // and insert "adjacent" pairs to heap
        // until get k smallest
        while (!heap.isEmpty() && k-- > 0) {
            int[] cur = heap.poll();
            // get index of min pair
            int i = cur[1];
            int j = cur[2];
            res.add(new int[] {a[i], b[j]});
            // only add neighbor nodes into heap
            // only add (i+1, j) when j == 0
            // so that always move j first, then move i
            if (j == 0 && i < m - 1) {
                heap.offer(new int[] {a[i + 1] + b[j], i + 1, j});
            }
            if (j < n - 1) {
                heap.offer(new int[] {a[i] + b[j + 1], i, j + 1});
            }
        }

        return res;
    }
}
