// each time poll a[i][j].value from pq, need to insert
// its neighbor => a[i][j-1].value into pq hence need node
class Node {
    int row;
    int col;
    int val;
    public Node(int row, int column, int value) {
        this.val = value;
        this.row = row;
        this.col = column;
    }
}

public class Solution {
    /*
     * @param arrays: a list of array
     * @param k: An integer
     * @return: an integer, K-th largest element in N arrays
     */
    public int KthInArrays(int[][] a, int k) {
        if(a == null || a.length == 0 || k <= 0){
            return -1;
        }
        // each array might have different length
        int m = a.length;
        int n;
        Node res;
        int i, j;
        // sort each array in ascending order
        for (i = 0; i< m; ++i) {
            Arrays.sort(a[i]);
        }
        // implement max heap with pq, size k
        Queue<Node> heap = new PriorityQueue<>(k, new Comparator<Node>(){
            public int compare(Node a, Node b){
                return b.val - a.val;
            }
        });
        // insert max value of each array into min heap
        for (i = 0; i< m; ++i) {
            //get length of i-th row
            n = a[i].length;
            if (n > 0) {
                // node contains row, col and val info
                Node cur = new Node(i, n - 1, a[i][n - 1]);
                heap.offer(cur);
            }
        }
        // poll and reload k times
        for (j = 0; j < k; j++) {
            res = heap.poll();
            if (j == k - 1) {
                return res.val;
            }
            // ensure cur element has left neighbor
            if (res.col > 0) {
                Node next = new Node(res.row, res.col - 1, a[res.row][res.col - 1]);
                heap.offer(next);
            }
        }
        // should not get here
        return -1;
    }
};
