// in each outer loop:
// (1) take the min value from heap, which is actually the i-th smallest number of matrix
// (2) put the right and down elements of current root into the heap and heapify
// (3) the k-th pulled out element is the k-th smallest number
// general idea is to utilize minHeap and figure out the i-th smallest number iteratively
class Solution {
    public int kthSmallest(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        // for a[i][j] its next bigger element must be a[i+1][j] or a[i][j+1]
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        // mark if current node has been added to heap before
        boolean[][] visit = new boolean[m][n];
        // build min heap
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((f, g) -> Integer.compare(f.val, g.val));
        pq.offer(new Pair(0, 0, a[0][0]));
        // poll first k - 1 elements from min heap
        // remaining root is kth smallest
        for (int i = 0; i < k - 1; ++i) {
            Pair cur = pq.poll();
            // add next 2 elements
            for (int j = 0; j < 2; ++j) {
                int nx = cur.x + dx[j];
                int ny = cur.y + dy[j];
                // check valid before adding to pq
                if (nx < m && ny < n && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    Pair next = new Pair(nx, ny, a[nx][ny]);
                    pq.offer(next);
                }
            }
        }

        return pq.peek().val;
    }
}

class Pair {
    public int x, y, val;
    public Pair(int a, int b, int c) {
        x = a;
        y = b;
        val = c;
    }
}
