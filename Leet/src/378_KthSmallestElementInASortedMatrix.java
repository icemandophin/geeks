// in each outer loop:
// (1) take the min value from heap, which is actually the i-th smallest number of matrix
// (2) put the right and down elements of current root into the heap and heapify
// (3) the k-th pulled out element is the k-th smallest number
// general idea is to utilize minHeap and figure out the i-th smallest number iteratively
class Pair {
    public int x, y, val;
    public Pair(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

class PairComparator implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        return a.val - b.val;
    }
}

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */

    public int kthSmallest(int[][] matrix, int k) {
        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};
        int n = matrix.length;
        int m = matrix[0].length;
        // mark if current node has been added to heap before
        boolean[][] hash = new boolean[n][m];
        PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(k, new PairComparator());
        int i, j, nx, ny;

        minHeap.add(new Pair(0, 0, matrix[0][0]));

        for(i=0; i<k-1; i++)
        {
            Pair cur = minHeap.poll();

            for(j=0; j<2; j++)
            {
                nx = cur.x + dx[j];
                ny = cur.y + dy[j];
                Pair next = new Pair(nx, ny, 0);

                if((nx < n) && (ny < m) && (!hash[nx][ny]))
                {
                    hash[nx][ny] = true;
                    next.val = matrix[nx][ny];
                    minHeap.add(next);
                }
            }
        }

        return minHeap.peek().val;
    }
}
